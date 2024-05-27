package com.carservicepro.user.mapper;

import com.carservicepro.user.dto.UserResponseDTO;
import com.carservicepro.user.entity.User;
import com.carservicepro.vehicle.dto.VehicleResponseDTO;
import com.carservicepro.vehicle.entity.Vehicle;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class MassiveImpl implements MassiveMapper {

    @Override
    public List<UserResponseDTO> asUserListResponseDTO(List<User> users) {
        if (users == null) {
            return null;
        }

        List<UserResponseDTO> list = new ArrayList<UserResponseDTO>(users.size());

        for (User user : users) {
            list.add(userToResponseDTO( user ));
        }
        return list;
    }

    protected VehicleResponseDTO vehicleToVehicleResponseDTO(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        Integer id = vehicle.getId();
        String brand = vehicle.getBrand();
        String modelName = vehicle.getModelName();
        double price = vehicle.getPrice();
        String imageModel = vehicle.getImageModel();

        VehicleResponseDTO vehicleResponseDTO = new VehicleResponseDTO( id, brand, modelName, price, imageModel );

        return vehicleResponseDTO;
    }

    protected List<VehicleResponseDTO> vehicleListToVehicleResponseDTOList(List<Vehicle> list) {
        if ( list == null ) {
            return null;
        }

        List<VehicleResponseDTO> list1 = new ArrayList<VehicleResponseDTO>( list.size() );
        for ( Vehicle vehicle : list ) {
            list1.add( vehicleToVehicleResponseDTO( vehicle ) );
        }

        return list1;
    }

    protected UserResponseDTO userToResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        Integer id = user.getId();
        String email = user.getEmail();
        String password = user.getPassword();
        List<VehicleResponseDTO> vehicles = vehicleListToVehicleResponseDTOList( user.getVehicles() );

        UserResponseDTO userResponseDTO = new UserResponseDTO( id, email, password, vehicles );

        return userResponseDTO;
    }
}
