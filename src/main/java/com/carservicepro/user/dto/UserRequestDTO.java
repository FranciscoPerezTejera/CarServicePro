package com.carservicepro.user.dto;

import com.carservicepro.vehicle.dto.VehicleRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class UserRequestDTO {

    private Integer id;
    private String password;
    private String email;
    private List<VehicleRequestDTO> vehicles;

}
