package adminpage.service.impl;

import adminpage.DTO.UserDTO;
import adminpage.DTO.request.PaginatedRequest;
import adminpage.entity.*;
import adminpage.entity.embedded.UserServiceAccessId;
import adminpage.repository.*;
import adminpage.service.UsersService;
import org.springframework.transaction.annotation.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final ClientRepository clientRepository;
    private final UserServiceAccessRepository usaRepository;
    private final UserClientAccessRepository ucaRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository, ServiceRepository serviceRepository,
                            ClientRepository clientRepository, UserServiceAccessRepository usaRepository,
                            UserClientAccessRepository ucaRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.clientRepository = clientRepository;
        this.usaRepository = usaRepository;
        this.ucaRepository = ucaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserEntity> getUserList(PaginatedRequest paginatedRequest){
        return userRepository.findByStatusEquals(1);
    }

    @Override
    public List<ServiceEntity> getUserServices(Long userId) {
        return serviceRepository.findAllServicesByUserId(userId);
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

    @Override
    @Transactional
    public UserServiceAccessEntity addUserServiceAccess(UserServiceAccessEntity userServiceAccessEntity) {
        if (userServiceAccessEntity == null) {
            throw new IllegalArgumentException("Provided UserServiceAccessEntity cannot be null");
        }

        UserServiceAccessId id = userServiceAccessEntity.getId();

        if (usaRepository.existsById(id)) {
            throw new IllegalArgumentException("Provided UserServiceAccessEntity already exists");
        }

        return usaRepository.save(userServiceAccessEntity);
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
        UserEntity userToEdit = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("User with provided ID not found"));

        userToEdit.updateWith(userDTO, passwordEncoder);
        return userRepository.save(userToEdit);
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
