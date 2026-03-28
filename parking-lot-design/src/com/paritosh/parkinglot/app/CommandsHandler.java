package com.paritosh.parkinglot.app;

import com.paritosh.parkinglot.constants.ParkingLotConstants;
import com.paritosh.parkinglot.enums.CommandsEnum;
import com.paritosh.parkinglot.enums.VehicleTypeEnum;
import com.paritosh.parkinglot.services.ParkingLotService;

import java.util.Map;

public class CommandsHandler {
    private final ParkingLotService parkingLotService;

    public CommandsHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public void handleCommand(String userCommandText) {
        String[] words = userCommandText.split(" ");
        if (words.length == 0) {
            System.out.println("> " + ParkingLotConstants.INVALID_COMMAND);
            return;
        }

        String command = words[0];
        if (!CommandsEnum.isValidCommand(command)) {
            System.out.println("> " + ParkingLotConstants.INVALID_COMMAND);
            return;
        }

        CommandsEnum commandsEnum = CommandsEnum.from(command);

        if (commandsEnum == CommandsEnum.PARK) {
            String vehicleNumber = words[1];
            String vehicleType = words[2];
            if (!VehicleTypeEnum.isValidVehicleType(vehicleType)) {
                System.out.println("> " + ParkingLotConstants.INVALID_VEHICLE_TYPE);
                return;
            }
            VehicleTypeEnum vehicleTypeEnum = VehicleTypeEnum.from(vehicleType);
            Map<String, String> response = parkingLotService.parkVehicle(vehicleNumber, vehicleTypeEnum);
            if (response.get(ParkingLotConstants.ERROR_MSG_KEY) != null) {
                System.out.println("> " + response.get(ParkingLotConstants.ERROR_MSG_KEY));
            } else {
                System.out.println("> " + ParkingLotConstants.RESPONSE_MSG + ": " + response.get(ParkingLotConstants.RESPONSE_MSG));
                System.out.println("> " + ParkingLotConstants.VEHICLE_NUMBER + ": " + response.get(ParkingLotConstants.VEHICLE_NUMBER));
                System.out.println("> " + ParkingLotConstants.VEHICLE_TYPE + ": " + response.get(ParkingLotConstants.VEHICLE_TYPE));
                System.out.println("> " + ParkingLotConstants.TICKET_NUMBER + ": " + response.get(ParkingLotConstants.TICKET_NUMBER));
                System.out.println("> " + ParkingLotConstants.FLOOR_NUMBER + ": " + response.get(ParkingLotConstants.FLOOR_NUMBER));
                System.out.println("> " + ParkingLotConstants.SPOT_NUMBER + ": " + response.get(ParkingLotConstants.SPOT_NUMBER));
                System.out.println("> " + ParkingLotConstants.ENTRY_TIME + ": " + response.get(ParkingLotConstants.ENTRY_TIME));
            }
        } else if (commandsEnum == CommandsEnum.UNPARK) {
            String vehicleNumber = words[1];
            Map<String, String> response = parkingLotService.unparkVehicle(vehicleNumber);
            if (response.get(ParkingLotConstants.ERROR_MSG_KEY) != null) {
                System.out.println("> " + response.get(ParkingLotConstants.ERROR_MSG_KEY));
            } else {
                System.out.println("> " + ParkingLotConstants.RESPONSE_MSG + ": " + response.get(ParkingLotConstants.RESPONSE_MSG));
                System.out.println("> " + ParkingLotConstants.VEHICLE_NUMBER + ": " + response.get(ParkingLotConstants.VEHICLE_NUMBER));
                System.out.println("> " + ParkingLotConstants.VEHICLE_TYPE + ": " + response.get(ParkingLotConstants.VEHICLE_TYPE));
                System.out.println("> " + ParkingLotConstants.TICKET_NUMBER + ": " + response.get(ParkingLotConstants.TICKET_NUMBER));
                System.out.println("> " + ParkingLotConstants.FLOOR_NUMBER + ": " + response.get(ParkingLotConstants.FLOOR_NUMBER));
                System.out.println("> " + ParkingLotConstants.SPOT_NUMBER + ": " + response.get(ParkingLotConstants.SPOT_NUMBER));
                System.out.println("> " + ParkingLotConstants.ENTRY_TIME + ": " + response.get(ParkingLotConstants.ENTRY_TIME));
                System.out.println("> " + ParkingLotConstants.EXIT_TIME + ": " + response.get(ParkingLotConstants.EXIT_TIME));
            }
        } else if (commandsEnum == CommandsEnum.SHOW_AVAILABLE_SPOTS) {
            Map<String, String> response = parkingLotService.showAvailableSpots();
            for (String key: response.keySet()) {
                System.out.println("> " + key + ": " + response.get(key));
            }
        } else if (commandsEnum == CommandsEnum.SHOW_OCCUPIED_SPOTS) {
            Map<String, Map<String, String>> response = parkingLotService.showOccupiedSpots();
            for (String floor : response.keySet()) {
                System.out.println("> " + floor);
                for (String spot : response.get(floor).keySet()) {
                    System.out.println("> " + spot + ": " + response.get(floor).get(spot));
                }
                System.out.println(); // add a line gap after each floor
            }
        } else if (commandsEnum == CommandsEnum.SEARCH) {
            String vehicleNumber = words[1];
            Map<String, String> response = parkingLotService.searchVehicle(vehicleNumber);

            if (response.get(ParkingLotConstants.ERROR_MSG_KEY) != null) {
                System.out.println("> " + response.get(ParkingLotConstants.ERROR_MSG_KEY));
            } else {
                System.out.println("> " + ParkingLotConstants.RESPONSE_MSG + ": " + response.get(ParkingLotConstants.RESPONSE_MSG));
                System.out.println("> " + ParkingLotConstants.VEHICLE_NUMBER + ": " + response.get(ParkingLotConstants.VEHICLE_NUMBER));
                System.out.println("> " + ParkingLotConstants.VEHICLE_TYPE + ": " + response.get(ParkingLotConstants.VEHICLE_TYPE));
                System.out.println("> " + ParkingLotConstants.TICKET_NUMBER + ": " + response.get(ParkingLotConstants.TICKET_NUMBER));
                System.out.println("> " + ParkingLotConstants.FLOOR_NUMBER + ": " + response.get(ParkingLotConstants.FLOOR_NUMBER));
                System.out.println("> " + ParkingLotConstants.SPOT_NUMBER + ": " + response.get(ParkingLotConstants.SPOT_NUMBER));
                System.out.println("> " + ParkingLotConstants.ENTRY_TIME + ": " + response.get(ParkingLotConstants.ENTRY_TIME));
            }
        } else if (commandsEnum == CommandsEnum.HELP) {
            System.out.println("Use the below commands to interact with the system");
            for (CommandsEnum c: CommandsEnum.values()) {
                System.out.println("> " + c.getValue());
            }
        } else if (commandsEnum == CommandsEnum.EXIT) {
            System.out.println("> " + "Closing the parking lot");
            System.exit(0);
        }
    }

}
