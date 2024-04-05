package adminpage.service;

import adminpage.DTO.request.PaginatedRequest;

public interface UsersService {
    UsersResponse getTableUsers(PaginatedRequest request);
}
