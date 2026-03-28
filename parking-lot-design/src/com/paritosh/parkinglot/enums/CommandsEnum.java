package com.paritosh.parkinglot.enums;

import com.paritosh.parkinglot.exceptions.InvalidCommandException;

public enum CommandsEnum {
    PARK("park <vehicle_number> <vehicle_type>"),
    UNPARK("unpark <vehicle_number>"),
    SHOW_AVAILABLE_SPOTS("show_available_spots"),
    SHOW_OCCUPIED_SPOTS("show_occupied_spots"),
    SEARCH("search <vehicle_number>"),
    EXIT("exit"),
    HELP("help");

    private String value;

    private CommandsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CommandsEnum from(String value) {
        try {
            return CommandsEnum.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new InvalidCommandException(value);
        }
    }

    public static boolean isValidCommand(String value) {
        try {
            CommandsEnum.valueOf(value.toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
