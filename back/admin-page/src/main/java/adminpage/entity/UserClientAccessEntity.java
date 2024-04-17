package adminpage.entity;


import adminpage.entity.embedded.UserClientAccessId;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "esb_auth_user_client_access", schema = "esb")
public class UserClientAccessEntity {
    @EmbeddedId
    private UserClientAccessId id;

    public UserClientAccessEntity() {
    }
    public UserClientAccessEntity(UserClientAccessId id) {
        this.id = id;
    }

}
