package adminpage.repository;


import adminpage.entity.UserServiceAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServiceAccessRepository extends JpaRepository<UserServiceAccessEntity, Long> {
}
