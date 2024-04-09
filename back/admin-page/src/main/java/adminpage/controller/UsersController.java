package adminpage.controller;

import adminpage.DTO.request.PaginatedRequest;
import adminpage.entity.ClientEntity;
import adminpage.entity.ServiceEntity;
import adminpage.entity.UserEntity;
import adminpage.entity.UserServiceAccessEntity;
import adminpage.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping("users/getUsers")
    public List<UserEntity> getUsers(@RequestBody PaginatedRequest request) {
        return usersService.getUserList(request);
    }

    @PostMapping("users/getUserServices")
    public List<ServiceEntity> getUserServices(@RequestParam Long userId) {
        return usersService.getUserServices(userId);
    }

    @PostMapping("users/getUserClients")
    public List<ClientEntity> getUserClients(@RequestParam Long userId) {
        return usersService.getUserClients(userId);
    }

    @PostMapping("users/addUser")
    public UserEntity addUser(@RequestBody UserEntity user) {
        return usersService.addUser(user);
    }

    @PostMapping("users/addUserService")
    public UserServiceAccessEntity addUserService(@RequestBody UserServiceAccessEntity userServiceAccessDTO) {
        return usersService.addUserServiceAccess(userServiceAccessDTO);
    }
}
