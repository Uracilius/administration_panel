package adminpage.service.impl;

import adminpage.DTO.request.PaginatedRequest;
import adminpage.entity.UserServiceAccessViewEntity;
import adminpage.repository.UserRepository;
import adminpage.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // Correct import
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    @Autowired // Add autowired annotation
    private UserRepository userRepository;

    @Override
    public List<UserServiceAccessViewEntity> getUserServiceAccess(PaginatedRequest paginatedRequest){
        Pageable currentPageInfo = PageRequest.of(paginatedRequest.getPage(), paginatedRequest.getPageSize());


        Page<UserServiceAccessViewEntity> userServiceAccessData =
                userRepository.findAll(currentPageInfo);


        return userServiceAccessData.getContent();
    }
}
