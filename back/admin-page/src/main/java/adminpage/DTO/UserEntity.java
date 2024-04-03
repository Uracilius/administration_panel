package adminpage.DTO;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;
}
