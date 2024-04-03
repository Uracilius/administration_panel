package adminpage.service.impl;

import adminpage.DTO.UserEntity;
import adminpage.DTO.response.LoginResponse;
import adminpage.DTO.request.LoginRequest;
import adminpage.repository.UserRepository;
import adminpage.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public LoginResponse login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        UserEntity user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return new LoginResponse("Imagine I generated this token", "OK");
        } else {
            return new LoginResponse("Nah bro", "NOT OK");
        }
    }
}

