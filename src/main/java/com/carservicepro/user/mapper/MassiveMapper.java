package com.carservicepro.user.mapper;

import com.carservicepro.user.dto.UserResponseDTO;
import com.carservicepro.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MassiveMapper {

    List<UserResponseDTO> asUserListResponseDTO(List<User> users);
}
