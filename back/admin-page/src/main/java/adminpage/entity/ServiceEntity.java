package adminpage.entity;

import adminpage.DTO.ServiceDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "esb_auth_services", schema = "esb")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(unique = true)
    private String name;

    private String url;

    private String description;

    private String shep_code;

    private Date created;

    private Date updated;

    private int status;

    public ServiceEntity() {
    }

    //Constructor for new service
    public ServiceEntity(ServiceDTO base){
        this.code = base.getCode();
        this.name = base.getName();
        this.description = base.getDescription();
        this.url = base.getUrl();
        this.created = new Date();
        this.updated = new Date();
        this.status = 1;
    }

    //method for updating service
    public void updateWith(ServiceDTO base){
        this.code = base.getCode();
        this.name = base.getName();
        this.description = base.getDescription();
        this.url = base.getUrl();
        this.updated = new Date();
    }

    public void softDelete(){
        this.status = 0;
    }
}
