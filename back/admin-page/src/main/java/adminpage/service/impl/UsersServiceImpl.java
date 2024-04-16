package adminpage.service.impl;

import adminpage.DTO.ServiceDTO;
import adminpage.DTO.UserDTO;
import adminpage.entity.*;
import adminpage.entity.embedded.UserServiceAccessId;
import adminpage.repository.*;
import adminpage.service.UsersService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final ClientRepository clientRepository;
    private final UserServiceAccessRepository usaRepository;
    private final UserClientAccessRepository ucaRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository,
                            ServiceRepository serviceRepository,
                            ClientRepository clientRepository,
                            UserServiceAccessRepository usaRepository,
                            UserClientAccessRepository ucaRepository,
                            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.clientRepository = clientRepository;
        this.usaRepository = usaRepository;
        this.ucaRepository = ucaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserEntity> getUserList(){
        return userRepository.findByStatusEqualsOrderById(1);
    }

    @Override

    public List<ServiceDTO> getUserServices(Long userId) {
        List<ServiceEntity> serviceEntities = serviceRepository.findAllServicesByUserId(userId);
        return serviceEntities.stream()
                .map(ServiceDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientEntity> getUserClients(Long userId) {
        return clientRepository.findAllClientsByUserId(userId);
    }

    @Override
    public UserEntity addUser(@NotNull UserDTO userDTO) {
        if (userRepository.existsByLogin(userDTO.getLogin())) {
            throw new IllegalStateException("User already exists with login: " + userDTO.getLogin());
        }
        UserEntity userEntity = new UserEntity(userDTO, passwordEncoder);
        return userRepository.save(userEntity);
    }

    @Transactional
    @Override
    public List<UserServiceAccessEntity> setUserServiceAccess(Long userId, List<Long> serviceIds) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (serviceIds.isEmpty()) {
            usaRepository.deleteByUserId(userId); // Assuming you have a method to delete all records for a user
            return Collections.emptyList(); // Return an empty list or appropriate response
        }

        List<ServiceEntity> services = serviceRepository.findAllById(serviceIds);
        if (services.size() != serviceIds.size()) {
            throw new EntityNotFoundException("One or more services not found");
        }

        List<UserServiceAccessEntity> usaEntities = new ArrayList<>();
        for (ServiceEntity service : services) {
            UserServiceAccessId usaId = new UserServiceAccessId(userId, service.getId());
            UserServiceAccessEntity usa = usaRepository.findById(usaId)
                    .orElse(new UserServiceAccessEntity(usaId)); // Create new or use existing

            usa.setUser(user);
            usa.setService(service);
            usaEntities.add(usa);
        }
        usaRepository.saveAll(usaEntities);

        return usaEntities;
    }

    @Override
    @Transactional
    public UserClientAccessEntity addUserClientAccess(UserClientAccessEntity userClientAccessEntity) {
        if (userClientAccessEntity == null) {
            throw new IllegalArgumentException("Provided UserClientAccessEntity cannot be null");
        }
        if (ucaRepository.existsByUserIdAndClientId(userClientAccessEntity.getUserId(), userClientAccessEntity.getClientId()) ) {
            throw new IllegalArgumentException("Provided UserClientAccessEntity already exists");
        }
        return ucaRepository.save(userClientAccessEntity);
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
    public ResponseEntity<String> deleteUserServiceAccess(Long userId, Long serviceId) {
        UserServiceAccessEntity usa = usaRepository.findByUserIdAndServiceId(userId, serviceId)
                .orElseThrow(() -> new IllegalArgumentException("User Service Access not found"));
        usaRepository.delete(usa);
        return ResponseEntity.ok("User Service Access deleted successfully");
    }

    @Override
    public ResponseEntity<String> deleteUserClientAccess(Long userId, Long clientId) {
        UserClientAccessEntity uca = ucaRepository.findByUserIdAndClientId(userId, clientId)
                .orElseThrow(() -> new IllegalArgumentException("User Client Access not found"));
        ucaRepository.delete(uca);
        return ResponseEntity.ok("User Client Access deleted successfully");
    }
}
