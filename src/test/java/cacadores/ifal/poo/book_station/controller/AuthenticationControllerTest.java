package cacadores.ifal.poo.book_station.controller;

import cacadores.ifal.poo.book_station.dto.Login.LoginResponse;
import cacadores.ifal.poo.book_station.dto.Login.LoginUserDto;
import cacadores.ifal.poo.book_station.dto.User.UserCreateDTO;
import cacadores.ifal.poo.book_station.model.entity.User;
import cacadores.ifal.poo.book_station.service.AuthenticationService;
import cacadores.ifal.poo.book_station.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        User registeredUser = new User();
        when(authenticationService.signup(userCreateDTO)).thenReturn(registeredUser);

        ResponseEntity<User> response = authenticationController.register(userCreateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(registeredUser, response.getBody());
        verify(authenticationService).signup(userCreateDTO);
    }

    @Test
    void testAuthenticate() {
        LoginUserDto loginUserDto = new LoginUserDto();
        User authenticatedUser = new User();
        String jwtToken = "testToken";
        long expirationTime = 3600L;

        when(authenticationService.authenticate(loginUserDto)).thenReturn(authenticatedUser);
        when(jwtService.generateToken(authenticatedUser)).thenReturn(jwtToken);
        when(jwtService.getExpirationTime()).thenReturn(expirationTime);

        ResponseEntity<LoginResponse> response = authenticationController.authenticate(loginUserDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(jwtToken, response.getBody().getToken());
        assertEquals(expirationTime, response.getBody().getExpiresIn());

        verify(authenticationService).authenticate(loginUserDto);
        verify(jwtService).generateToken(authenticatedUser);
        verify(jwtService).getExpirationTime();
    }
}