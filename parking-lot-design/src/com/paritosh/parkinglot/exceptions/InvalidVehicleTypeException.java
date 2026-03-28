package com.paritosh.parkinglot.exceptions;

public class InvalidVehicleTypeException extends ApplicationException {
    public InvalidVehicleTypeException(String value) {
        super("vehicle type: " + value + " is invalid", "INVALID_VEHICLE_TYPE");
    }
}
