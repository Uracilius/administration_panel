package adminpage.controller;

import adminpage.DTO.response.LoginResponse;
import adminpage.DTO.request.LoginRequest;
import adminpage.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        System.out.println("Received login request with credentials: "+ request.toString());
        return loginService.login(request);
    }

    @PostMapping("/register")
    public LoginResponse register(@RequestBody LoginRequest request) {
        return loginService.register(request);
    }
}
