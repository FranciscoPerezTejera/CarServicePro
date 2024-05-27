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
public class UtilsUser implements UserMapper {
    @Override
    public UserResponseDTO asUserResponse(User user) {
        if ( user == null ) {
            return null;
        }
        Integer id = user.getId();
        String email = user.getEmail();
        String password = user.getPassword();
        List<VehicleResponseDTO> vehicles = vehicleListToVehicleResponseDTOList(user.getVehicles());
        UserResponseDTO userResponseDTO = new UserResponseDTO(id, email, password, vehicles);

        return userResponseDTO;
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

    protected VehicleResponseDTO vehicleToVehicleResponseDTO(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        Integer id = vehicle.getId();
        String brand = vehicle.getBrand();
        String modelName = vehicle.getModelName();
        double price = vehicle.getPrice();
        String imageModel = vehicle.getImageModel();
        User user = vehicle.getUser();

        VehicleResponseDTO vehicleResponseDTO = new VehicleResponseDTO( id, brand, modelName, price, imageModel );

        return vehicleResponseDTO;
    }

    public List<UserResponseDTO> asUserResponseList(List<User> users) {
        if (users == null) {
            return null;
        }

        List<UserResponseDTO> list = new ArrayList<UserResponseDTO>();

        for (User user : users) {
            list.add(asUsereResponse(user));
        }

        return list;
    }

    private UserResponseDTO asUsereResponse(User user) {

        if (user == null) {
            return null;
        }

        Integer id = user.getId();
        String email = user.getEmail();
        String password = user.getPassword();
        List<VehicleResponseDTO>vehicles = userVehiclesToVeiclesResponseDTOList(user.getVehicles());

        UserResponseDTO userResponseDTO = new UserResponseDTO(id, email, password, vehicles);

        return null;
    }

    private List<VehicleResponseDTO> userVehiclesToVeiclesResponseDTOList(List<Vehicle> vehicles) {

        if (vehicles == null) {
            return null;
        }

        List<VehicleResponseDTO> vehicleResponseDTOList = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            Integer id = vehicle.getId();
            String brand = vehicle.getBrand();
            String modelName = vehicle.getModelName();
            double price = vehicle.getPrice();
            String imageName = vehicle.getImageModel();

            VehicleResponseDTO vehicleResponseDTO = new VehicleResponseDTO(id, brand, modelName, price, imageName);
            vehicleResponseDTOList.add(vehicleResponseDTO);
        }

        return vehicleResponseDTOList;
    }
}
