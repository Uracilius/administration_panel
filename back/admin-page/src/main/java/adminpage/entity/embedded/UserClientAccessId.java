package adminpage.entity.embedded;

import java.io.Serializable;

public class UserClientAccessId implements Serializable {
    private Long userId;
    private Long clientId;

    public UserClientAccessId() {}
    public UserClientAccessId(Long userId, Long clientId) {
        this.userId = userId;
        this.clientId = clientId;
    }
}
