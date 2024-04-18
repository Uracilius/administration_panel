package adminpage.service.impl;

import adminpage.DTO.UserDTO;
import adminpage.entity.*;
import adminpage.repository.*;
import adminpage.validator.GlobalValidator;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsersServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ServiceRepository serviceRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private GlobalValidator globalValidator;
    @Mock
    private UserServiceAccessRepository usaRepository;
    @Mock
    private UserClientAccessRepository ucaRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsersServiceImpl usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
    }

    @Test
    @DisplayName("getUserList returns active users")
    void getUserListReturnsActiveUsers() {
        when(userRepository.findByStatusEqualsOrderById(1)).thenReturn(Collections.singletonList(new UserEntity()));
        assertEquals(1, usersService.getUserList().size());
    }

    @Test
    void getUserServicesReturnsServicesForUser() {
        when(serviceRepository.findAllServicesByUserId(anyLong())).thenReturn(Collections.singletonList(new ServiceEntity()));
        assertEquals(1, usersService.getUserServices(1L).size());
    }

    @Test
    void getUserClientsReturnsClientsForUser() {
        when(clientRepository.findAllClientsByUserId(anyLong())).thenReturn(Collections.singletonList(new ClientEntity()));
        assertEquals(1, usersService.getUserClients(1L).size());
    }

    @Test
    void addUserSavesNewUserWhenUserDoesNotExist() {
        when(userRepository.existsByLogin(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(new UserEntity());
        UserDTO userDTO = new UserDTO(
                1L,                // User ID
                "john.doe",        // Login
                "John Doe's account", // Description
                "password123"      // Password
        );
        assertNotNull(usersService.addUser(userDTO));
    }
//
//    @Test
//    void addUserServiceAccessListThrowsExceptionWhenUserDoesNotExist() {
//        when(userRepository.existsById(anyLong())).thenReturn(false);
//        assertThrows(EntityNotFoundException.class, () -> usersService.addUserServiceAccessList(1L, Arrays.asList(1L, 2L)));
//    }
//
//    @Test
//    void addUserServiceAccessListThrowsExceptionWhenServiceDoesNotExist() {
//        long serviceId = 10000000;
//
//        when(serviceRepository.findAllById(any())).thenReturn(Collections.singletonList(new ServiceEntity()));
//        assertThrows(EntityNotFoundException.class, () -> usersService.addUserServiceAccessList(1L, Arrays.asList(serviceId, 2L)));
//    }

    @Test
    void addUserServiceAccessListAddsAccessWhenUserAndServicesExist() {
        when(userRepository.existsById(anyLong())).thenReturn(true);
        when(serviceRepository.findAllById(any())).thenReturn(Arrays.asList(new ServiceEntity(), new ServiceEntity()));
        when(usaRepository.saveAll(any())).thenReturn(Collections.singletonList(new UserServiceAccessEntity()));
        assertEquals(2, usersService.addUserServiceAccessList(1L, Arrays.asList(1L, 2L)).size());
    }

//    @Test
//    void addUserClientAccessListThrowsExceptionWhenUserDoesNotExist() {
//        when(userRepository.existsById(anyLong())).thenReturn(false);
//        assertThrows(EntityNotFoundException.class, () -> usersService.addUserClientAccessList(1L, Arrays.asList(1L, 2L)));
//    }
//
//    @Test
//    void addUserClientAccessListThrowsExceptionWhenClientDoesNotExist() {
//        when(userRepository.existsById(anyLong())).thenReturn(true);
//        when(clientRepository.findAllById(any())).thenReturn(Collections.singletonList(new ClientEntity()));
//        assertThrows(EntityNotFoundException.class, () -> usersService.addUserClientAccessList(1L, Arrays.asList(1L, 2L)));
//    }

    @Test
    void addUserClientAccessListAddsAccessWhenUserAndClientsExist() {
        when(userRepository.existsById(anyLong())).thenReturn(true);
        when(clientRepository.findAllById(any())).thenReturn(Arrays.asList(new ClientEntity(), new ClientEntity()));
        when(ucaRepository.saveAll(any())).thenReturn(Collections.singletonList(new UserClientAccessEntity()));
        assertEquals(2, usersService.addUserClientAccessList(1L, Arrays.asList(1L, 2L)).size());
    }

    @Test
    void editUserThrowsExceptionWhenUserIdIsNull() {
        UserDTO userDTO = new UserDTO(
                1L,                // User ID
                "john.doe",        // Login
                "John Doe's account", // Description
                "password123"      // Password
        );
        userDTO.setId(null);
        assertThrows(IllegalArgumentException.class, () -> usersService.editUser(userDTO));
    }

    @Test
    void editUserThrowsExceptionWhenUserDoesNotExist() {
        UserDTO userDTO = new UserDTO(
                1L,                // User ID
                "john.doe",        // Login
                "John Doe's account", // Description
                "password123"      // Password
        );
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> usersService.editUser(userDTO));
    }

    @Test
    void editUserUpdatesUserWhenUserExists() {
        UserDTO userDTO = new UserDTO(
                1L,                // User ID
                "john.doe",        // Login
                "John Doe's account", // Description
                "password123"      // Password
        );
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new UserEntity()));
        when(userRepository.save(any())).thenReturn(new UserEntity());
        assertNotNull(usersService.editUser(userDTO));
    }

    @Test
    void deleteUserReturnsNotFoundWhenUserDoesNotExist() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertEquals(HttpStatus.NOT_FOUND, usersService.deleteUser(1L).getStatusCode());
    }

    @Test
    void deleteUserDeletesUserWhenUserExists() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new UserEntity()));
        assertEquals(HttpStatus.OK, usersService.deleteUser(1L).getStatusCode());
    }

    @Test
    void deleteUserServiceAccessThrowsExceptionWhenAccessDoesNotExist() {
        when(usaRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> usersService.deleteUserServiceAccess(1L, 1L));
    }

    @Test
    void deleteUserServiceAccessDeletesAccessWhenAccessExists() {
        when(usaRepository.findById(any())).thenReturn(Optional.of(new UserServiceAccessEntity()));
        assertEquals(HttpStatus.OK, usersService.deleteUserServiceAccess(1L, 1L).getStatusCode());
    }

    @Test
    void deleteUserClientAccessThrowsExceptionWhenAccessDoesNotExist() {
        when(ucaRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> usersService.deleteUserClientAccess(1L, 1L));
    }

    @Test
    void deleteUserClientAccessDeletesAccessWhenAccessExists() {
        when(ucaRepository.findById(any())).thenReturn(Optional.of(new UserClientAccessEntity()));
        assertEquals(HttpStatus.OK, usersService.deleteUserClientAccess(1L, 1L).getStatusCode());
    }
}