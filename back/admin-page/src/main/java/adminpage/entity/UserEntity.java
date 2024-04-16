package adminpage.entity;

import adminpage.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Entity
@Data
@Table(name = "esb_auth_users", schema = "esb")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String login;

    private String pass;

    private Date created;

    private Date updated;

    private int status;

    private String description;

    public UserEntity() {
    }
    public UserEntity(UserDTO base, PasswordEncoder passwordEncoder){
        this.login = base.getLogin();
        this.pass = passwordEncoder.encode(base.getPass());
        this.description = base.getDescription();
        this.created = new Date();
        this.updated = new Date();
        this.status = 1;
    }

    public void updateWith(UserDTO base, PasswordEncoder passwordEncoder){
        this.login = base.getLogin();
        this.pass = passwordEncoder.encode(base.getPass());
        this.description = base.getDescription();
        this.updated = new Date();
    }

    public void softDelete(){
        this.status = 0;
    }
}
