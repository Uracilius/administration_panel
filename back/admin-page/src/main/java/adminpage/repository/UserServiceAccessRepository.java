package adminpage.repository;


import adminpage.entity.UserServiceAccessEntity;
import adminpage.entity.embedded.UserServiceAccessId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserServiceAccessRepository extends JpaRepository<UserServiceAccessEntity, UserServiceAccessId> {
    Optional<UserServiceAccessEntity> findById(UserServiceAccessId usa);

    @Transactional
    void deleteById_UserId(Long userId);

    @Transactional
    void deleteById(UserServiceAccessId id);
}
