package adminpage.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "test_login", schema = "esb")
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;
}
