package adminpage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "esb_auth_services", schema = "esb")
public class ServiceEntity {
    @Id
    private Long id;

    private String code;

    @Column(unique = true)
    private String name;

    private String description;

    private String shep_code;

    private Date created;

    private Date updated;

    private int status;

}
