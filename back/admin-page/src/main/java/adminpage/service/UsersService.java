package adminpage.service;

import adminpage.DTO.request.PaginatedRequest;
import adminpage.entity.ClientEntity;
import adminpage.entity.ServiceEntity;
import adminpage.entity.UserEntity;

import java.util.List;

public interface UsersService {
    List<UserEntity> getUserList(PaginatedRequest request);

    UserEntity editUser(UserEntity editedUser);

    List<ServiceEntity> getUserServices(Long userId);

    List<ClientEntity> getUserClients(Long userId);
}
