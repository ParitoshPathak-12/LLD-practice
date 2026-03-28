package com.paritosh.parkinglot.services;

import com.paritosh.parkinglot.constants.ParkingLotConstants;
import com.paritosh.parkinglot.enums.VehicleTypeEnum;
import com.paritosh.parkinglot.models.*;
import com.paritosh.parkinglot.utils.ParkingLotUtil;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingLotService {
    Map<String, VehicleParkingDetails> currentVehicleDetails; /* stores currently parked vehicle details */

    List<ParkedVehicleHistory> parkedVehicleHistories; /* stores all unparked vehicles details */

    Vehicle[][] parkingLotStatus; /* parkingLotStatus[i][j] = Vehicle parked at floor i and spot j */

    ParkingLot parkingLot;

    PriorityQueue<ParkingSpot> availableSpotsPQ;

    public ParkingLotService(int totalFloors, int spotsPerFloor, String lotName) {
        currentVehicleDetails = new HashMap<>();
        parkedVehicleHistories = new ArrayList<>();
        parkingLot = new ParkingLot(totalFloors, spotsPerFloor, lotName);
        parkingLotStatus = new Vehicle[totalFloors+1][spotsPerFloor+1];
        availableSpotsPQ = new PriorityQueue<>((p1, p2) -> {
            if (p1.getFloorNumber() != p2.getFloorNumber()) {
                return p1.getFloorNumber() - p2.getFloorNumber();
            }
            return p1.getSpotNumber() - p2.getSpotNumber();
        });
        for (int i = 1; i <= totalFloors; i++) {
            for (int j = 1; j <= spotsPerFloor; j++) {
                ParkingSpot parkingSpot = new ParkingSpot(i, j);
                availableSpotsPQ.add(parkingSpot);
            }
        }
    }

    public Map<String, String> parkVehicle(String vehicleNumber, VehicleTypeEnum vehicleTypeEnum) {
        Map<String, String> response = new HashMap<>();
        response.put(ParkingLotConstants.ERROR_MSG_KEY, null);

        // check if any spot available
        if (!isAnySpotAvailable()) {
            response.put(ParkingLotConstants.ERROR_MSG_KEY, ParkingLotConstants.NO_SPOTS_AVAILABLE);
            return response;
        }

        // check if vehicleNumber already in parking
        if (isVehicleInParking(vehicleNumber)) {
            response.put(ParkingLotConstants.ERROR_MSG_KEY, ParkingLotConstants.VEHICLE_IN_PARKING);
            return response;
        }

        // create Vehicle
        Vehicle vehicle = new Vehicle(vehicleNumber, vehicleTypeEnum);

        // assign ParkingSpot
        ParkingSpot parkingSpot = availableSpotsPQ.poll(); // remove and return smallest

        // generate Ticket
        Ticket ticket = new Ticket(
                ParkingLotUtil.getTicketId(),
                vehicle,
                parkingSpot,
                LocalDateTime.now()
        );

        // create VehicleParkingDetails
        VehicleParkingDetails vehicleParkingDetails = new VehicleParkingDetails(vehicle, parkingSpot, ticket);

        // store in currentVehicleDetails
        currentVehicleDetails.put(vehicleNumber, vehicleParkingDetails);

        parkingLotStatus[parkingSpot.getFloorNumber()][parkingSpot.getSpotNumber()] = vehicle;

        response.put(ParkingLotConstants.RESPONSE_MSG, ParkingLotConstants.PARKED_SUCCESSFULLY);
        response.put(ParkingLotConstants.VEHICLE_NUMBER, vehicle.getVehicleNumber());
        response.put(ParkingLotConstants.VEHICLE_TYPE, vehicle.getVehicleTypeEnum().name());
        response.put(ParkingLotConstants.TICKET_NUMBER, ticket.getTicketId());
        response.put(ParkingLotConstants.FLOOR_NUMBER, String.valueOf(parkingSpot.getFloorNumber()));
        response.put(ParkingLotConstants.SPOT_NUMBER, String.valueOf(parkingSpot.getSpotNumber()));
        response.put(ParkingLotConstants.ENTRY_TIME, ticket.getEntryTime().toString());
        return response;
    }

    public Map<String, String> unparkVehicle(String vehicleNumber) {
        Map<String, String> response = new HashMap<>();
        response.put(ParkingLotConstants.ERROR_MSG_KEY, null);

        // check if vehicle is parked or not
        if (!isVehicleInParking(vehicleNumber)) {
            response.put(ParkingLotConstants.ERROR_MSG_KEY, ParkingLotConstants.VEHICLE_NOT_IN_PARKING);
            return response;
        }

        // get vehicleParkingDetails
        VehicleParkingDetails vehicleParkingDetails = currentVehicleDetails.get(vehicleNumber);
        ParkingSpot parkingSpot = vehicleParkingDetails.getParkingSpot();
        Vehicle vehicle = vehicleParkingDetails.getVehicle();
        Ticket ticket = vehicleParkingDetails.getTicket();

        // remove from currentVehicleDetails
        currentVehicleDetails.remove(vehicleNumber);

        // set parkingLotStatus to be null
        parkingLotStatus[parkingSpot.getFloorNumber()][parkingSpot.getSpotNumber()] = null;

        // add availableSpotsPQ
        availableSpotsPQ.add(parkingSpot);

        ParkedVehicleHistory parkedVehicleHistory = new ParkedVehicleHistory(vehicle, parkingSpot, ticket, LocalDateTime.now());
        parkedVehicleHistories.add(parkedVehicleHistory);

        response.put(ParkingLotConstants.RESPONSE_MSG, ParkingLotConstants.UNPARKED_SUCCESSFULLY);
        response.put(ParkingLotConstants.VEHICLE_NUMBER, vehicle.getVehicleNumber());
        response.put(ParkingLotConstants.VEHICLE_TYPE, vehicle.getVehicleTypeEnum().name());
        response.put(ParkingLotConstants.TICKET_NUMBER, ticket.getTicketId());
        response.put(ParkingLotConstants.FLOOR_NUMBER, String.valueOf(parkingSpot.getFloorNumber()));
        response.put(ParkingLotConstants.SPOT_NUMBER, String.valueOf(parkingSpot.getSpotNumber()));
        response.put(ParkingLotConstants.ENTRY_TIME, ticket.getEntryTime().toString());
        response.put(ParkingLotConstants.EXIT_TIME, parkedVehicleHistory.getExitTime().toString());
        return response;
    }

    public Map<String, String> showAvailableSpots() {
        int floors = parkingLot.getTotalFloors();
        int spots = parkingLot.getSpotsPerFloor();

        Map<String, String> response = new TreeMap<>();

        for (int i = 1; i <= floors; i++) {
            List<String> availableSpotsNumber = new ArrayList<>();
            for (int j = 1; j <= spots; j++) {
                if (parkingLotStatus[i][j] == null) {
                    availableSpotsNumber.add(String.valueOf(j));
                }
            }
            String key = ParkingLotConstants.FLOOR_PREFIX + i;
            String value = "Spots: " + availableSpotsNumber;
            response.put(key, value);
        }

        return response;
    }

    public Map<String, Map<String, String>> showOccupiedSpots() {
        int floors = parkingLot.getTotalFloors();
        int spots = parkingLot.getSpotsPerFloor();

        Map<String, Map<String, String>> response = new TreeMap<>();

        for (int i = 1; i <= floors; i++) {
            Map<String, String> floorWiseDetails = new TreeMap<>();
            for (int j = 1; j <= spots; j++) {
                if (parkingLotStatus[i][j] != null) {
                    String spotKey = ParkingLotConstants.SPOT_PREFIX + j;
                    String spotValue = "Occupied by: " + parkingLotStatus[i][j].getVehicleNumber();
                    floorWiseDetails.put(spotKey, spotValue);
                }
            }
            String floorKey = ParkingLotConstants.FLOOR_PREFIX + i;
            response.put(floorKey, floorWiseDetails);
        }

        return response;
    }

    public Map<String, String> searchVehicle(String vehicleNumber) {
        Map<String, String> response = new HashMap<>();
        response.put(ParkingLotConstants.ERROR_MSG_KEY, null);

        if (!isVehicleInParking(vehicleNumber)) {
            response.put(ParkingLotConstants.ERROR_MSG_KEY, ParkingLotConstants.VEHICLE_NOT_IN_PARKING);
            return response;
        }

        VehicleParkingDetails vehicleParkingDetails = currentVehicleDetails.get(vehicleNumber);
        Vehicle vehicle = vehicleParkingDetails.getVehicle();
        ParkingSpot parkingSpot = vehicleParkingDetails.getParkingSpot();
        Ticket ticket = vehicleParkingDetails.getTicket();

        response.put(ParkingLotConstants.RESPONSE_MSG, ParkingLotConstants.VEHICLE_FOUND_IN_PARKING);
        response.put(ParkingLotConstants.VEHICLE_NUMBER, vehicle.getVehicleNumber());
        response.put(ParkingLotConstants.VEHICLE_TYPE, vehicle.getVehicleTypeEnum().name());
        response.put(ParkingLotConstants.TICKET_NUMBER, ticket.getTicketId());
        response.put(ParkingLotConstants.FLOOR_NUMBER, String.valueOf(parkingSpot.getFloorNumber()));
        response.put(ParkingLotConstants.SPOT_NUMBER, String.valueOf(parkingSpot.getSpotNumber()));
        response.put(ParkingLotConstants.ENTRY_TIME, ticket.getEntryTime().toString());
        return response;
    }

    private boolean isAnySpotAvailable() {
        return !availableSpotsPQ.isEmpty();
    }

    private boolean isVehicleInParking(String vehicleNumber) {
        return currentVehicleDetails.containsKey(vehicleNumber);
    }

}
