package adminpage.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String login;

    private String pass;
    private String description;

    public UserDTO(Long id, String login, String description, String pass) {
        this.id = id;
        this.login = login;
        this.description = description;
        this.pass = pass;
    }
}
