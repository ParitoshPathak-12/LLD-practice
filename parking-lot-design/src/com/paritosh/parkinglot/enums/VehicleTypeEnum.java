package com.paritosh.parkinglot.enums;

import com.paritosh.parkinglot.exceptions.InvalidVehicleTypeException;

public enum VehicleTypeEnum {
    BIKE, CAR, TRUCK;

    public static VehicleTypeEnum from(String value) {
        try {
            return VehicleTypeEnum.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new InvalidVehicleTypeException(value);
        }
    }

    public static boolean isValidVehicleType(String value) {
        try {
            VehicleTypeEnum.valueOf(value.toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
