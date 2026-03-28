package com.paritosh.parkinglot.models;

public class ParkingSpot {
    private int floorNumber;
    private int spotNumber;

    public ParkingSpot(int floorNumber, int spotNumber) {
        this.floorNumber = floorNumber;
        this.spotNumber = spotNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }
}
