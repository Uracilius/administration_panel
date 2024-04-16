package adminpage.DTO;

import adminpage.entity.ServiceEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDTO {
    private Long id;

    private String code;

    private String name;

    private String url;

    private String description;

    public ServiceDTO() {}

    public ServiceDTO(Long id, String code, String name, String url, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public ServiceDTO(ServiceEntity serviceEntity){
        this.id = serviceEntity.getId();
        this.code = serviceEntity.getCode();
        this.name = serviceEntity.getName();
        this.url = serviceEntity.getUrl();
        this.description = serviceEntity.getDescription();
    }
}
