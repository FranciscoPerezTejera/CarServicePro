package com.carservicepro.vehicle.mapper;

import com.carservicepro.vehicle.dto.VehicleRequestDTO;
import com.carservicepro.vehicle.dto.VehicleResponseDTO;
import com.carservicepro.vehicle.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper
public interface VehicleMapper {

    Vehicle asVehicle(VehicleRequestDTO vehicleRequestDTO);

    Vehicle asUpdateVehicle(@MappingTarget Vehicle vehicle, VehicleRequestDTO vehicleRequestDTO);

    List<VehicleResponseDTO> asVehicleResponseList(List<Vehicle> vehicles);

    VehicleResponseDTO asVehicleResponse(Vehicle vehicle);

    @Mapping(target = "vehicle.id", ignore = true)
    List<Vehicle> asUpdateListVehicles(@MappingTarget List<Vehicle> vehicles, List<VehicleRequestDTO> vehiclesDTO);

    List<Vehicle> asListVehicles(List<VehicleRequestDTO> vehicles);



}
