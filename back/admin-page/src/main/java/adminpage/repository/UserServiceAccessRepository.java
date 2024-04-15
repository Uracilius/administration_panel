package adminpage.repository;


import adminpage.entity.UserServiceAccessEntity;
import adminpage.entity.embedded.UserServiceAccessId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserServiceAccessRepository extends JpaRepository<UserServiceAccessEntity, UserServiceAccessId> {
    Optional<UserServiceAccessEntity> findByUserIdAndServiceId(Long userId, Long serviceId);


}
