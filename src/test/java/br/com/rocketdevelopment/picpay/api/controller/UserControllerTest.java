package br.com.rocketdevelopment.picpay.api.controller;

import br.com.rocketdevelopment.picpay.api.dto.UserDTO;
import br.com.rocketdevelopment.picpay.domain.users.UserType;
import br.com.rocketdevelopment.picpay.serivce.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserDTO userDTO;

    @BeforeEach
    public void setup() {
        userDTO = new UserDTO("Marcel", "12345678901", "joao.silva@example.com","suaSenhaSegura123",new BigDecimal(1000), UserType.COMMON);
    }

    @Test
    public void testRegister() {
        when(userService.create(any(UserDTO.class))).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.register(userDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDTO, response.getBody());

        verify(userService, times(1)).create(any(UserDTO.class));
    }

    @Test
    public void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(userDTO));

        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonList(userDTO), response.getBody());

        verify(userService, times(1)).getAllUsers();
    }
}