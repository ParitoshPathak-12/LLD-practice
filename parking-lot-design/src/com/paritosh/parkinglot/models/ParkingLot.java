package com.paritosh.parkinglot.models;

public class ParkingLot {
    private int totalFloors;
    private int spotsPerFloor;
    private String parkingLotName;

    public ParkingLot(int totalFloors, int spotsPerFloor, String parkingLotName) {
        this.totalFloors = totalFloors;
        this.spotsPerFloor = spotsPerFloor;
        this.parkingLotName = parkingLotName;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(int totalFloors) {
        this.totalFloors = totalFloors;
    }

    public int getSpotsPerFloor() {
        return spotsPerFloor;
    }

    public void setSpotsPerFloor(int spotsPerFloor) {
        this.spotsPerFloor = spotsPerFloor;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }
}
