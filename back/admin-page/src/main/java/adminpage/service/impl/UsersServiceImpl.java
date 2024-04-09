package adminpage.service.impl;

import adminpage.DTO.request.PaginatedRequest;
import adminpage.entity.ClientEntity;
import adminpage.entity.ServiceEntity;
import adminpage.entity.UserEntity;
import adminpage.entity.UserServiceAccessEntity;
import adminpage.repository.ClientRepository;
import adminpage.repository.ServiceRepository;
import adminpage.repository.UserRepository;
import adminpage.repository.UserServiceAccessRepository;
import adminpage.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; 
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UserServiceAccessRepository usaRepository;
    @Override
    public List<UserEntity> getUserList(PaginatedRequest paginatedRequest){
        Pageable currentPageInfo = PageRequest.of(paginatedRequest.getPage(), paginatedRequest.getPageSize());

        Page<UserEntity> userServiceAccessData =
                userRepository.findAll(currentPageInfo);

        return userServiceAccessData.getContent();
    }

    @Override
    public UserEntity editUser(UserEntity editedUser) {

        if (editedUser.getId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        if(userRepository.findById(editedUser.getId())!=null){
            editedUser.setUpdated(new Date());
            userRepository.save(editedUser);
            return editedUser;
        }
        else{
            //TODO: ERROR HANDLING
            return null;
        }
    }

    @Override
    public List<ServiceEntity> getUserServices(Long userId) {
        return serviceRepository.findAllServicesByUserId(userId);
    }

    @Override
    public List<ClientEntity> getUserClients(Long userId) {
        return clientRepository.findAllClientsByUserId(userId);
    }

    @Override
    public UserEntity addUser(UserEntity userToAdd) {
        userToAdd.setUpdated(new Date());
        userToAdd.setCreated(new Date());
        userRepository.save(userToAdd);
        return userToAdd;
    }

    @Override
    public UserServiceAccessEntity addUserServiceAccess(UserServiceAccessEntity usa) {
        usaRepository.save(usa);
        return null;
    }

}
