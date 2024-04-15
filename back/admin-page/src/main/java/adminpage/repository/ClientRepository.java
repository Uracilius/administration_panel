package adminpage.repository;

import adminpage.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query("SELECT ce FROM UserClientAccessEntity uca JOIN ClientEntity ce ON uca.clientId = ce.id WHERE uca.userId = ?1")
    List<ClientEntity> findAllClientsByUserId(Long userId);
}
