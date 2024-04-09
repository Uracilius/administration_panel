package adminpage.repository;

import adminpage.entity.ServiceEntity;
import adminpage.entity.UserServiceAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    @Query("SELECT se FROM UserServiceAccessEntity usa JOIN ServiceEntity se ON usa.service_id = se.id WHERE usa.user_id = ?1")
    List<ServiceEntity> findAllServicesByUserId(Long userId);
}
