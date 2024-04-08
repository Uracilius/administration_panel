package adminpage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "esb_auth_user_service_access", schema = "esb")
public class UserServiceAccess {
    @Id
    private Long user_id;

    private Long client_id;

}
