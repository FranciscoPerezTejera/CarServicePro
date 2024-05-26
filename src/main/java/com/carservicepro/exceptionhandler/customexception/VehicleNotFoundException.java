package com.carservicepro.exceptionhandler.customexception;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException (String message) {
        super(message);
    }

}
