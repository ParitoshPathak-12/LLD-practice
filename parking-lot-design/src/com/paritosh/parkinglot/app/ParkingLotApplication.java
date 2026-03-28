package com.paritosh.parkinglot.app;

import com.paritosh.parkinglot.services.ParkingLotService;

import java.util.Scanner;

public class ParkingLotApplication {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        boolean isParkingLotInitialized = false;

        int floors = 0, spots = 0;

        String parkingLotName = null;

        ParkingLotService parkingLotService = null;

        CommandsHandler commandsHandler = null;

        while (true) {
            if (!isParkingLotInitialized) {
                System.out.println("Initialize the parking lot: ");
                System.out.println("Enter the parking lot name: ");
                parkingLotName = scanner.nextLine();
                System.out.println("Enter the number of floors: ");
                floors = scanner.nextInt(); scanner.nextLine();
                System.out.println("Enter the number of spots per floor:");
                spots = scanner.nextInt(); scanner.nextLine();
                parkingLotService = new ParkingLotService(floors, spots, parkingLotName);
                commandsHandler = new CommandsHandler(parkingLotService);
                isParkingLotInitialized = true;
                System.out.println("Parking Lot created successfully, start interacting using commands (type help to know the commands)");
            } else {
                String userCommand = scanner.nextLine();
                commandsHandler.handleCommand(userCommand);
                System.out.println("--------------------------------------------------------");
            }
        }

    }
}
