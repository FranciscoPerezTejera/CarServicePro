package com.carservicepro.user.service;

import com.carservicepro.user.dto.UserRequestDTO;
import com.carservicepro.user.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    void saveUser(UserRequestDTO userRequestDTO);

    void updateUser(UserRequestDTO userRequestDTO);

    UserResponseDTO findByUserEmail(String email);

    List<UserResponseDTO> findAll();

}
