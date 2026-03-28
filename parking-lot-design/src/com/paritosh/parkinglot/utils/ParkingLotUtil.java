package com.paritosh.parkinglot.utils;

import com.paritosh.parkinglot.constants.ParkingLotConstants;

public class ParkingLotUtil {
    private static int ticketSequence = 0;

    public static String getTicketId() {
        ticketSequence++;
        return ParkingLotConstants.TICKET_ID_PREFIX + ticketSequence;
    }
}
