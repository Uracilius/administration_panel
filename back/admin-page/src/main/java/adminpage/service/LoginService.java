package adminpage.service;

import adminpage.DTO.response.LoginResponse;
import adminpage.DTO.request.LoginRequest;

public interface LoginService {

    LoginResponse login(LoginRequest request);

    LoginResponse register(LoginRequest request);

}
