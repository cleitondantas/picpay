package br.com.rocketdevelopment.picpay.api.controller;


import br.com.rocketdevelopment.picpay.api.dto.UserDTO;
import br.com.rocketdevelopment.picpay.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO user) {
        UserDTO userDTO = userService.create(user);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsers = userService.getAllUsers();

        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
}
