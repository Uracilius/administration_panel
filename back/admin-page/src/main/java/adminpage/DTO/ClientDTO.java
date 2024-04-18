package adminpage.DTO;

import adminpage.entity.ClientEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {
    private Long id;

    private String code;

    private String name;

    private String url;

    private String description;

    public ClientDTO() {}

    public ClientDTO(Long id, String code, String name, String url, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public ClientDTO(ClientEntity clientEntity){
        this.id = clientEntity.getId();
        this.code = clientEntity.getCode();
        this.name = clientEntity.getName();
        this.url = clientEntity.getUrl();
        this.description = clientEntity.getDescription();
    }
}
