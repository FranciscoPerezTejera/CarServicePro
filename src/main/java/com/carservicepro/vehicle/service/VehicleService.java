package com.carservicepro.vehicle.service;

import com.carservicepro.vehicle.dto.VehicleRequestDTO;
import com.carservicepro.vehicle.dto.VehicleResponseDTO;

import java.util.List;

public interface VehicleService {

    void saveVehicle(VehicleRequestDTO vehicleRequestDTO);

    void deleteVehicleById(Integer id);

    void updateVehicle(VehicleRequestDTO vehicleRequestDTO);

    List<VehicleResponseDTO> findAll();

    List<VehicleResponseDTO> findById(Integer id);

}
