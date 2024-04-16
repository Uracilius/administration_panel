package adminpage.controller;

import adminpage.DTO.ServiceDTO;
import adminpage.DTO.UserDTO;
import adminpage.entity.*;
import adminpage.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping()
    public List<UserEntity> getUsers() {
        return usersService.getUserList();
    }

    @GetMapping("{userId}/services")
    public List<ServiceDTO> getUserServices(@PathVariable Long userId) {
        return usersService.getUserServices(userId);
    }

    @GetMapping("{userId}/clients")
    public List<ClientEntity> getUserClients(@PathVariable Long userId) {
        return usersService.getUserClients(userId);
    }

    @PostMapping("user")
    public UserEntity addUser(@RequestBody UserDTO user) {
        return usersService.addUser(user);
    }

    @PostMapping ("service-access/{userId}")
    public List<UserServiceAccessEntity> setUserServiceAccess(@PathVariable Long userId, @RequestBody List<Long> serviceIds) {
        return usersService.setUserServiceAccess(userId, serviceIds);
    }

    @PostMapping("client-access")
    public UserClientAccessEntity addUserClientAccess(@RequestBody UserClientAccessEntity userClientAccessEntity) {
        return usersService.addUserClientAccess(userClientAccessEntity);
    }

    @PatchMapping("edit")
    public ResponseEntity<UserEntity> editUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(usersService.editUser(user));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        return usersService.deleteUser(userId);
    }

    @DeleteMapping("service-access/{userId}/{serviceId}")
    public ResponseEntity<String> deleteUserServiceAccess(@PathVariable Long userId, @PathVariable Long serviceId) {
        return usersService.deleteUserServiceAccess(userId, serviceId);
    }

    @DeleteMapping("client-access/{userId}/{clientId}")
    public ResponseEntity<String> deleteUserClientAccess(@PathVariable Long userId, @PathVariable Long clientId) {
        return usersService.deleteUserClientAccess(userId, clientId);
    }
}

