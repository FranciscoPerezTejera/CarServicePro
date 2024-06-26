package com.carservicepro.exceptionhandler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String message;

}