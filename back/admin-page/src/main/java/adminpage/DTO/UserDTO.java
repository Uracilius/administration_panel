package adminpage.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    @NotBlank(message = "Login must not be empty")
    private String login;
    @NotBlank(message = "Password must not be empty")
    private String pass;
    private String description;

    public UserDTO(Long id, String login, String description, String pass) {
        this.id = id;
        this.login = login;
        this.description = description;
        this.pass = pass;
    }
}
