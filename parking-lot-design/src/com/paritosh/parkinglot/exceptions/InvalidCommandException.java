package com.paritosh.parkinglot.exceptions;

public class InvalidCommandException extends ApplicationException {
    public InvalidCommandException(String value) {
        super("command: " + value + " is invalid", "INVALID_COMMAND");
    }
}
