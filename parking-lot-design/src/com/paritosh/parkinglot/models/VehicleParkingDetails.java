package com.paritosh.parkinglot.models;

public class VehicleParkingDetails {
    public Vehicle vehicle;
    public ParkingSpot parkingSpot;
    public Ticket ticket;

    public VehicleParkingDetails(Vehicle vehicle, ParkingSpot parkingSpot, Ticket ticket) {
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.ticket = ticket;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
