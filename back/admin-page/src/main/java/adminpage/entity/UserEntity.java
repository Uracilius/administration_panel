package adminpage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "esb_auth_users", schema = "esb")
public class UserEntity {
    @Id
    private Long id;

    @Column(unique = true)
    private String login;

    private String pass;

    private Date created;

    private Date updated;

    private int status;

    private String description;
}
