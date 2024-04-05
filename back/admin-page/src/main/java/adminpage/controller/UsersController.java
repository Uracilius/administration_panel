package adminpage.controller;

import adminpage.DTO.request.PaginatedRequest;
import adminpage.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping("users/getTableUsers")
    public UsersResponse getTableUsers(PaginatedRequest request) {
        return usersService.getTableUsers(request);
    }
}
