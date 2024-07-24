package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.dto.User.UserCreateDTO;
import cacadores.ifal.poo.book_station.dto.User.UserUpdateDTO;
import cacadores.ifal.poo.book_station.model.entity.User;
import cacadores.ifal.poo.book_station.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;
    private UserCreateDTO userCreateDTO;
    private UserUpdateDTO userUpdateDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        userCreateDTO = new UserCreateDTO();
        userCreateDTO.setEmail("test@example.com");
        userUpdateDTO = new UserUpdateDTO();
    }

    @Test
    void getAllUsers() {
        List<User> users = Arrays.asList(user);
        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void getUserById() {
        String id = "1";
        when(userService.getUserById(id)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getUserById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void createUser() {
        when(userService.existsByEmail(anyString())).thenReturn(false);
        when(userService.createUser(userCreateDTO)).thenReturn(user);

        ResponseEntity<User> response = userController.createUser(userCreateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void createUserConflict() {
        when(userService.existsByEmail(anyString())).thenReturn(true);

        ResponseEntity<User> response = userController.createUser(userCreateDTO);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void updateUser() {
        String id = "1";
        when(userService.updateUser(id, userUpdateDTO)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.updateUser(id, userUpdateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void deleteUser() {
        String id = "1";

        ResponseEntity<Void> response = userController.deleteUser(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).deleteUser(id);
    }
}