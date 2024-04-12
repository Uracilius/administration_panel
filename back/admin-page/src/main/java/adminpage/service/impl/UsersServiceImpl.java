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
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable; 
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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

        List<UserEntity> userList = userRepository.findByStatusEquals(1);

        return userList;
    }

    @Override
    public UserEntity editUser(UserEntity editedUser) {

        if (editedUser.getId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        if(userRepository.existsById(editedUser.getId())){
            editedUser.setUpdated(new Date());
            editedUser.setPass(passwordEncoder.encode(editedUser.getPass()));
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
        //TODO: Check for replacement
        userToAdd.setUpdated(new Date());
        userToAdd.setCreated(new Date());
        userToAdd.setStatus(1);
        userRepository.save(userToAdd);
        return userToAdd;
    }

    //TODO: Check for better error handling
    @Override
    public ResponseEntity<String> deleteUser(Long userId){
        if (userRepository.existsById(userId)){
            UserEntity user = userRepository.getById(userId);
            user.setStatus(0);
            userRepository.save(user);
            return ResponseEntity.ok("User deleted successfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    //TODO: better implementation
    @Override
    public UserServiceAccessEntity addUserServiceAccess(UserServiceAccessEntity usa) {
        usaRepository.save(usa);
        return null;
    }

}
