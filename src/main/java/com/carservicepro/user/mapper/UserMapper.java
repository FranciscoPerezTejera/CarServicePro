package com.carservicepro.user.mapper;

import com.carservicepro.user.dto.UserRequestDTO;
import com.carservicepro.user.dto.UserResponseDTO;
import com.carservicepro.user.entity.User;
import com.carservicepro.vehicle.entity.Vehicle;
import com.carservicepro.vehicle.mapper.VehicleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Mapper
public interface UserMapper {

    VehicleMapper VEHICLE_MAPPER = Mappers.getMapper(VehicleMapper.class);

    UserResponseDTO asUserResponse(User user);

    default User asUser(UserRequestDTO userRequestDTO, String password) {
        if (userRequestDTO == null) {
            return null;
        }

        User user = new User();
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(password);

        List<Vehicle> vehicles = VEHICLE_MAPPER.asListVehicles(userRequestDTO.getVehicles());

        if (!CollectionUtils.isEmpty(vehicles)) {
            vehicles.forEach(vehicle -> {
                vehicle.setUser(user);
            });
            user.setVehicles(vehicles);
        }

        return user;
    }

    default User asUpdateUser(User user, UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) {
            return null;
        }

        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());

        List<Vehicle> vehicles = VEHICLE_MAPPER.asUpdateListVehicles(user.getVehicles(), userRequestDTO.getVehicles());

        if (!CollectionUtils.isEmpty(vehicles)) {
            vehicles.forEach(vehicle -> {
                vehicle.setUser(user);
            });
            user.setVehicles(vehicles);
        }

        return user;
    }
}

