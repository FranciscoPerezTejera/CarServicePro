package com.carservicepro.exceptionhandler.customexception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException (String message) {
        super(message);
    }

}
