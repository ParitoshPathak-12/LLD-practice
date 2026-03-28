package com.paritosh.parkinglot.models;

import com.paritosh.parkinglot.enums.VehicleTypeEnum;

public class Vehicle {
    private String vehicleNumber;
    private VehicleTypeEnum vehicleTypeEnum;

    public Vehicle(String vehicleNumber, VehicleTypeEnum vehicleTypeEnum) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleTypeEnum = vehicleTypeEnum;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleTypeEnum getVehicleTypeEnum() {
        return vehicleTypeEnum;
    }

    public void setVehicleTypeEnum(VehicleTypeEnum vehicleTypeEnum) {
        this.vehicleTypeEnum = vehicleTypeEnum;
    }
}
