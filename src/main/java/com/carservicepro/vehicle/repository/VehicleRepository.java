package com.carservicepro.vehicle.repository;

import com.carservicepro.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    void deleteById(Integer id);
    Optional<Vehicle> findById(Integer id);
    List<Vehicle> findVehicleById(Integer id);
}
