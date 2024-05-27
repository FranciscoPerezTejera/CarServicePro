package com.carservicepro.user.service.impl;

import com.carservicepro.exceptionhandler.customexception.UserNotFoundException;
import com.carservicepro.user.dto.UserRequestDTO;
import com.carservicepro.user.dto.UserResponseDTO;
import com.carservicepro.user.entity.User;
import com.carservicepro.user.mapper.MassiveMapper;
import com.carservicepro.user.mapper.UtilsUser;
import com.carservicepro.user.repository.UserRepository;
import com.carservicepro.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    //private final UserMapper userMapper;
    private final UtilsUser utils;
    private final MassiveMapper massiveMapper;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void saveUser(UserRequestDTO userRequestDTO) {

        String passwordEncrypted = hashPassword(userRequestDTO.getPassword());

       User user = utils.asUser(userRequestDTO, passwordEncrypted);
        log.info("save user: {}", user.getEmail());
        userRepository.saveAndFlush(user);
    }

    @Override
    public void updateUser(UserRequestDTO userRequestDTO) {

        String passwordEncrypted = hashPassword(userRequestDTO.getPassword());

        User response = userRepository.findById(userRequestDTO.getId()).orElseThrow(
                () -> new UserNotFoundException(String.format("Car not found by code : %s", userRequestDTO.getId())));

        userRequestDTO.setPassword(passwordEncrypted);
        
        User user = utils.asUpdateUser(response, userRequestDTO);
        userRepository.save(user);
    }

    @Override
    public UserResponseDTO findByUserEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(String.format("Car not found user by username : %s", email))
        );

        return utils.asUserResponse(user);
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return massiveMapper.asUserListResponseDTO(userRepository.findAll());
    }

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
