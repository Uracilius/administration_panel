package adminpage.repository;

import adminpage.entity.ClientEntity;
import adminpage.entity.ServiceEntity;
import ch.qos.logback.core.net.server.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query("SELECT s FROM ClientEntity s JOIN UserClientAccessEntity uca ON s.id = uca.id.clientId WHERE uca.id.userId = ?1")
    List<ClientEntity> findAllClientsByUserId(Long userId);

    List<ClientEntity> findByStatusEquals(int status);
}
