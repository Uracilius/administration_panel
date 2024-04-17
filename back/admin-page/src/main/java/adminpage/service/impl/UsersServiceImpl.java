package adminpage.service.impl;

import adminpage.DTO.ServiceDTO;
import adminpage.DTO.UserDTO;
import adminpage.entity.*;
import adminpage.entity.embedded.UserClientAccessId;
import adminpage.entity.embedded.UserServiceAccessId;
import adminpage.repository.*;
import adminpage.service.UsersService;
import adminpage.validator.GlobalValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final ClientRepository clientRepository;
    private final UserServiceAccessRepository usaRepository;
    private final UserClientAccessRepository ucaRepository;
    private final PasswordEncoder passwordEncoder;

    private final GlobalValidator globalValidator;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository,
                            ServiceRepository serviceRepository,
                            ClientRepository clientRepository,
                            UserServiceAccessRepository usaRepository,
                            UserClientAccessRepository ucaRepository,
                            GlobalValidator globalValidator,
                            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.clientRepository = clientRepository;
        this.usaRepository = usaRepository;
        this.ucaRepository = ucaRepository;
        this.passwordEncoder = passwordEncoder;
        this.globalValidator = globalValidator;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserEntity> getUserList(){
        return userRepository.findByStatusEqualsOrderById(1);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceDTO> getUserServices(Long userId) {
        List<ServiceEntity> serviceEntities = serviceRepository.findAllServicesByUserId(userId);
        return serviceEntities.stream()
                .map(ServiceDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientEntity> getUserClients(Long userId) {
        return clientRepository.findAllClientsByUserId(userId);
    }

    @Override
    public UserEntity addUser(UserDTO userDTO) {
        globalValidator.checkLoginExists(userDTO.getLogin());

        UserEntity userEntity = new UserEntity(userDTO, passwordEncoder);
        return userRepository.save(userEntity);
    }

    @Transactional
    @Override
    public List<UserServiceAccessEntity> addUserServiceAccessList(Long userId, List<Long> serviceIds) {
        globalValidator.checkListEmpty(serviceIds);

        globalValidator.checkExistsById(userRepository, userId);

        List<ServiceEntity> services = serviceRepository.findAllById(serviceIds);
        globalValidator.checkListsSameSize(services, serviceIds, "One or more services ahs not been found");

        List<UserServiceAccessEntity> usaEntities = new ArrayList<>();
        for (ServiceEntity service : services) {
            UserServiceAccessId usaId = new UserServiceAccessId(userId, service.getId());
            Optional<UserServiceAccessEntity> existingUsa = usaRepository.findById(usaId);

            globalValidator.checkAlreadyPresent(existingUsa);

            usaEntities.add(new UserServiceAccessEntity(usaId));
        }
        usaRepository.saveAll(usaEntities);

        return usaEntities;
    }

    @Transactional
    @Override
    public List<UserClientAccessEntity> addUserClientAccessList(Long userId, List<Long> clientIds) {
        globalValidator.checkListEmpty(clientIds);

        globalValidator.checkExistsById(userRepository, userId);

        List<ClientEntity> clients = clientRepository.findAllById(clientIds);
        globalValidator.checkListsSameSize(clients, clientIds, "One or more clients has not been found");

        List<UserClientAccessEntity> ucaEntities = new ArrayList<>();
        for (ClientEntity client : clients) {
            UserClientAccessId ucaId = new UserClientAccessId(userId, client.getId());
            Optional<UserClientAccessEntity> existingUca = ucaRepository.findById(ucaId);
            globalValidator.checkAlreadyPresent(existingUca);

            ucaEntities.add(new UserClientAccessEntity(ucaId));
        }

        ucaRepository.saveAll(ucaEntities);

        return ucaEntities;
    }

    @Override
    public UserEntity editUser(UserDTO userDTO) {
        if (userDTO.getId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        UserEntity userEntity = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("User with provided ID not found"));

        userEntity.updateWith(userDTO, passwordEncoder);
        return userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteUser(Long userId){
        return userRepository.findById(userId)
                .map(user -> {
                    user.softDelete();
                    userRepository.save(user);
                    return ResponseEntity.ok("User deleted successfully");
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteUserServiceAccess(Long userId, Long serviceId) {
        UserServiceAccessEntity usa = usaRepository.findById(new UserServiceAccessId(userId, serviceId))
                .orElseThrow(() -> new IllegalArgumentException("User Service Access not found"));
        usaRepository.delete(usa);
        return ResponseEntity.ok("User Service Access deleted successfully");
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteUserClientAccess(Long userId, Long clientId) {
        UserClientAccessEntity uca = ucaRepository.findById(new UserClientAccessId(userId, clientId))
                .orElseThrow(() -> new IllegalArgumentException("User Client Access not found"));
        ucaRepository.delete(uca);
        return ResponseEntity.ok("User Client Access deleted successfully");
    }
}
