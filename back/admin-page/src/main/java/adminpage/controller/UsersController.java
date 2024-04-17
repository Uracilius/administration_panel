package adminpage.controller;


import adminpage.DTO.ServiceDTO;
import adminpage.DTO.UserDTO;
import adminpage.entity.*;
import adminpage.service.UsersService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
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
    public List<ServiceDTO> getUserServices(@NotNull @PathVariable Long userId) {
        return usersService.getUserServices(userId);
    }

    @GetMapping("{userId}/clients")
    public List<ClientEntity> getUserClients(@NotNull @PathVariable Long userId) {
        return usersService.getUserClients(userId);
    }

    @PostMapping("user")
    public UserEntity addUser(@Valid @RequestBody UserDTO user) {
        return usersService.addUser(user);
    }

    @PostMapping ("service-access/{userId}")
    public List<UserServiceAccessEntity> addUserServiceAccess(@NotNull @PathVariable Long userId,@NotEmpty @RequestBody List<Long> serviceIds) {
        return usersService.addUserServiceAccessList(userId, serviceIds);
    }

    @PostMapping("client-access/{userId}")
    public List<UserClientAccessEntity> addUserClientAccess(@NotNull @PathVariable Long userId, @NotEmpty @RequestBody List<Long> clientIds) {
        return usersService.addUserClientAccessList(userId, clientIds);
    }

    @PatchMapping("edit")
    public ResponseEntity<UserEntity> editUser(@Valid @RequestBody UserDTO user) {
        return ResponseEntity.ok(usersService.editUser(user));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@NotNull @PathVariable Long userId) {
        return usersService.deleteUser(userId);
    }

    @DeleteMapping("service-access/{userId}/{serviceId}")
    public ResponseEntity<String> deleteUserServiceAccess(@NotNull @PathVariable Long userId, @NotNull @PathVariable Long serviceId) {
        return usersService.deleteUserServiceAccess(userId, serviceId);
    }

    @DeleteMapping("client-access/{userId}/{clientId}")
    public ResponseEntity<String> deleteUserClientAccess(@NotNull @PathVariable Long userId, @NotNull @PathVariable Long clientId) {
        return usersService.deleteUserClientAccess(userId, clientId);
    }
}

