package adminpage.entity;

import adminpage.entity.embedded.UserServiceAccessId;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "esb_auth_user_service_access", schema = "esb")
public class UserServiceAccessEntity {

    @EmbeddedId
    private UserServiceAccessId id;

    public UserServiceAccessEntity() {}
    public UserServiceAccessEntity(UserServiceAccessId id) {
        this.id = id;
    }
}
