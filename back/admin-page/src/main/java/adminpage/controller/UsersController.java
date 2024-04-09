package adminpage.controller;

import adminpage.DTO.request.PaginatedRequest;
import adminpage.entity.ServiceEntity;
import adminpage.entity.UserEntity;
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
}
