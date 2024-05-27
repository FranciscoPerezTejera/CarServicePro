package com.carservicepro.vehicle.mapper;

import com.carservicepro.user.entity.User;
import com.carservicepro.user.service.UserService;
import com.carservicepro.vehicle.dto.VehicleRequestDTO;
import com.carservicepro.vehicle.dto.VehicleResponseDTO;
import com.carservicepro.vehicle.entity.Vehicle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UtilsVehicle implements VehicleMapper {

    private UserService userService;

    @Override
    public Vehicle asVehicle(VehicleRequestDTO vehicleRequestDTO) {
        if (vehicleRequestDTO == null) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setBrand(vehicleRequestDTO.getBrand());
        vehicle.setModelName(vehicleRequestDTO.getModelName());
        vehicle.setPrice(vehicleRequestDTO.getPrice());
        vehicle.setImageModel(vehicleRequestDTO.getImageModel());

        return vehicle;
    }

    @Override
    public Vehicle asUpdateVehicle(Vehicle vehicle, VehicleRequestDTO vehicleRequestDTO) {

        if (vehicleRequestDTO == null) {
            return null;
        }

        vehicle.setBrand(vehicleRequestDTO.getBrand());
        vehicle.setModelName(vehicleRequestDTO.getModelName());
        vehicle.setImageModel(vehicleRequestDTO.getImageModel());
        vehicle.setPrice(vehicleRequestDTO.getPrice());

        return vehicle;
    }

    @Override
    public List<VehicleResponseDTO> asVehicleResponseList(List<Vehicle> vehicles) {

        if (vehicles == null) {
            return null;
        }

        List<VehicleResponseDTO> list = new ArrayList<VehicleResponseDTO>();

        for (Vehicle vehicle : vehicles) {
            list.add(asVehicleResponse(vehicle));
        }

        return list;
    }

    @Override
    public VehicleResponseDTO asVehicleResponse(Vehicle vehicle) {

        if (vehicle == null) {
            return null;
        }

        Integer id = vehicle.getId();
        String brand = vehicle.getBrand();
        String modelName = vehicle.getModelName();
        String imageName = vehicle.getImageModel();
        double price = vehicle.getPrice();

        VehicleResponseDTO vehicleResponseDTO = new VehicleResponseDTO(id, brand, modelName, price, imageName);

        return vehicleResponseDTO;
    }

    @Override
    public List<Vehicle> asUpdateListVehicles(List<Vehicle> vehicles, List<VehicleRequestDTO> vehiclesDTO) {
        return null;
    }

    @Override
    public List<Vehicle> asListVehicles(List<VehicleRequestDTO> vehicles) {
        return null;
    }
}
