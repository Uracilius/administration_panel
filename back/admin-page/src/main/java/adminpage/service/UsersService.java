package adminpage.service;

import adminpage.DTO.ServiceDTO;
import adminpage.DTO.UserDTO;
import adminpage.entity.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsersService {
    List<UserDTO> getUserList();

    UserEntity editUser(UserDTO user);

    List<ServiceDTO> getUserServices(Long userId);

    List<UserClientAccessEntity> addUserClientAccessList(Long userId, List<Long> clientIds);

    List<UserServiceAccessEntity> addUserServiceAccessList(Long userId, List<Long> serviceIds);

    ResponseEntity<String> deleteUserServiceAccess(Long userId, Long serviceId);

    List<ClientEntity> getUserClients(Long userId);

    ResponseEntity<String> deleteUserClientAccess(Long userId, Long clientId);

    UserEntity addUser(UserDTO user);

    ResponseEntity<String> deleteUser(Long userId);




}
