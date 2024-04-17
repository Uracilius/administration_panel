package adminpage.repository;


import adminpage.entity.UserClientAccessEntity;
import adminpage.entity.embedded.UserClientAccessId;
import adminpage.entity.embedded.UserServiceAccessId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserClientAccessRepository  extends JpaRepository<UserClientAccessEntity, UserClientAccessId>{
    Optional<UserClientAccessEntity> findById(UserClientAccessId uca);

    boolean existsById(UserClientAccessId userClientAccessId);

    @Transactional
    void deleteById(UserClientAccessId id);
}
