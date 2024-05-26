package com.carservicepro.user.controller;

import com.carservicepro.user.dto.UserRequestDTO;
import com.carservicepro.user.dto.UserResponseDTO;
import com.carservicepro.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carservicepro/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody UserRequestDTO userRequestDTO) {
        userService.saveUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        userService.updateUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/email")
    public ResponseEntity<UserResponseDTO> getByUsername(@RequestParam("email") String email, @RequestParam("password") String password) {
        return ResponseEntity.ok(userService.findByUserEmail(email, password));
    }


}
