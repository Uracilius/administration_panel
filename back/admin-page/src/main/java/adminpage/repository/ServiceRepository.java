package adminpage.repository;

import adminpage.entity.ServiceEntity;
import adminpage.entity.UserEntity;
import adminpage.entity.UserServiceAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    @Query("SELECT usa.service FROM UserServiceAccessEntity usa WHERE usa.user.id = ?1")
    List<ServiceEntity> findAllServicesByUserId(Long userId);

    List<ServiceEntity> findByStatusEquals(int status);


}
