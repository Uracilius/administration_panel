package adminpage.DTO.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String authToken;
    private String code;
    public LoginResponse(String authToken, String code) {
        this.authToken = authToken;
        this.code = code;
    }
}
