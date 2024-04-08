package adminpage.service;

import adminpage.DTO.request.PaginatedRequest;
import adminpage.entity.UserServiceAccessViewEntity;

import java.util.List;

public interface UsersService {
    List<UserServiceAccessViewEntity> getUserServiceAccess(PaginatedRequest request);
}
