package adminpage.service.impl;

import adminpage.entity.LoginEntity;
import adminpage.DTO.response.LoginResponse;
import adminpage.DTO.request.LoginRequest;
import adminpage.repository.LoginRepository;
import adminpage.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginRepository loginRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Corrected variable declaration and assignment

    @Override
    public LoginResponse login(LoginRequest request) {
        String username = request.getUsername();
        String password = passwordEncoder.encode(request.getPassword()); // Encoding password before comparison is unnecessary

        LoginEntity user = loginRepository.findByUsername(username);

        // You should use passwordEncoder.matches() to compare encoded password
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new LoginResponse("Imagine I generated this token", "OK");
        } else {
            return new LoginResponse("Nah bro", "NOT OK");
        }
    }

    public LoginResponse register(LoginRequest request){

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        LoginEntity entityToSave = new LoginEntity();
        entityToSave.setUsername(request.getUsername());
        entityToSave.setPassword(encodedPassword);
        loginRepository.save(entityToSave);
        return new LoginResponse("Hey friend, password set" +
                "", "OK");
    }
}
