package com.carservicepro.vehicle.dto;

import com.carservicepro.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseDTO {

    private Integer id;

    private String brand;

    private String modelName;

    private double price;

    private String imageModel;
}
