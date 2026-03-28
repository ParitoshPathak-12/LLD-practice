package com.paritosh.parkinglot.models;

import java.time.LocalDateTime;

public class ParkedVehicleHistory extends VehicleParkingDetails {
    private LocalDateTime exitTime;

    public ParkedVehicleHistory(Vehicle vehicle, ParkingSpot parkingSpot, Ticket ticket, LocalDateTime exitTime) {
        super(vehicle, parkingSpot, ticket);
        this.exitTime = exitTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }
}
