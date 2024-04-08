package adminpage.controller;

import adminpage.DTO.request.PaginatedRequest;
import adminpage.entity.UserServiceAccessViewEntity;
import adminpage.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersService usersService;

    @CrossOrigin
    @PostMapping("users/getUserServiceAccess")
    public List<UserServiceAccessViewEntity> getUserServiceAccess(@RequestBody PaginatedRequest request) {
        return usersService.getUserServiceAccess(request);
    }

}
