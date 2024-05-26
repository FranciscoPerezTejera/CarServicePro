package com.carservicepro.vehicle.service.impl;

import com.carservicepro.exceptionhandler.customexception.VehicleNotFoundException;
import com.carservicepro.vehicle.dto.VehicleRequestDTO;
import com.carservicepro.vehicle.dto.VehicleResponseDTO;
import com.carservicepro.vehicle.entity.Vehicle;
import com.carservicepro.vehicle.mapper.VehicleMapper;
import com.carservicepro.vehicle.repository.VehicleRepository;
import com.carservicepro.vehicle.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper modelMapper;

    @Override
    public void saveVehicle(VehicleRequestDTO vehicleRequestDTO) {
        Vehicle vehicle = modelMapper.asVehicle(vehicleRequestDTO);
        log.info("save vehicle: {}", vehicle.getId());
        vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicleById(Integer id) {
        log.info("delete model by id: {}", id);
        vehicleRepository.deleteById(id);
    }

    @Override
    public void updateVehicle(VehicleRequestDTO vehicleRequestDTO) {

        Vehicle response = vehicleRepository.findById(vehicleRequestDTO.getId()).orElseThrow(
                () -> new VehicleNotFoundException(String.format("Vehicle not found by code : %s", vehicleRequestDTO.getId())));

        Vehicle vehicle = modelMapper.asUpdateVehicle(response,vehicleRequestDTO);
        log.info("update response by code: {}", vehicle.getId());
        vehicleRepository.save(vehicle);

    }

    @Override
    public List<VehicleResponseDTO> findAll() {
        return modelMapper.asVehicleResponseList(vehicleRepository.findAll());

    }

    @Override
    public List<VehicleResponseDTO> findById(Integer id) {
        List<Vehicle> models = vehicleRepository.findVehicleById(id);

        return modelMapper.asVehicleResponseList(models);
    }
}
