package adminpage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "esb_auth_clients", schema = "esb")
public class ClientEntity {
    @Id
    private Long id;

    private String code;
    @Column(unique = true)
    private String name;

    private String url;

    private String shep_code;

    private String description;

    private Date created;

    private Date updated;

    private int status;
}
