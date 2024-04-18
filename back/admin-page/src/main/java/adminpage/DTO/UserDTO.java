package adminpage.DTO;

import adminpage.entity.UserEntity;
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

    public UserDTO(UserEntity userEntity){
        this.id = userEntity.getId();
        this.login = userEntity.getLogin();
        this.description = userEntity.getDescription();
        this.pass = userEntity.getPass();
    }
}
