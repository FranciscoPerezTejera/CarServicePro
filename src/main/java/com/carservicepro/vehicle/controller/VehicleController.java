package com.carservicepro.vehicle.controller;


import com.carservicepro.vehicle.dto.VehicleRequestDTO;
import com.carservicepro.vehicle.dto.VehicleResponseDTO;
import com.carservicepro.vehicle.entity.Vehicle;
import com.carservicepro.vehicle.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/carservicepro/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Void> saveVehicle(@RequestBody VehicleRequestDTO vehicleRequestDTO) {
        vehicleService.saveVehicle(vehicleRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVehicleById(@PathVariable Integer id) {
        vehicleService.deleteVehicleById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateVehicle(@RequestBody VehicleRequestDTO vehicleRequestDTO) {
        vehicleService.updateVehicle(vehicleRequestDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> getAll() {
        return ResponseEntity.ok(vehicleService.findAll());
    }
    @GetMapping("/id")
    public ResponseEntity<List <VehicleResponseDTO>> getById(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(vehicleService.findById(id));
    }

}
