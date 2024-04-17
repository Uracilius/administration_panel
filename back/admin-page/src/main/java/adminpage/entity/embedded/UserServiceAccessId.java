package adminpage.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class UserServiceAccessId implements Serializable {
    private Long userId;
    private Long serviceId;

    public UserServiceAccessId() {}
    public UserServiceAccessId(Long userId, Long serviceId) {
        this.userId = userId;
        this.serviceId = serviceId;
    }
}
