package adminpage.repository;

import adminpage.entity.ServiceEntity;
import adminpage.entity.UserEntity;
import adminpage.entity.UserServiceAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    @Query("SELECT s FROM ServiceEntity s JOIN UserServiceAccessEntity usa ON s.id = usa.id.serviceId WHERE usa.id.userId = ?1")
    List<ServiceEntity> findAllServicesByUserId(Long userId);

    List<ServiceEntity> findByStatusEquals(int status);


}
