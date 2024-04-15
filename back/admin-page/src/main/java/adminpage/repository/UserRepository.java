package adminpage.repository;

import adminpage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByStatusEquals(int status);

    UserEntity getById(Long id);

    boolean existsByLogin(String login);
}
