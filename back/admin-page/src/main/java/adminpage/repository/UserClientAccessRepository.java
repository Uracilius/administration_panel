package adminpage.repository;


import adminpage.entity.UserClientAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserClientAccessRepository  extends JpaRepository<UserClientAccessEntity, Long>{
    Optional<UserClientAccessEntity> findByUserIdAndClientId(Long userId, Long clientId);

    boolean existsByUserIdAndClientId(Long userId, Long clientId);
}
