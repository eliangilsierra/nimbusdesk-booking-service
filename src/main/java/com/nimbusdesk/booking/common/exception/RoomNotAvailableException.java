package com.nimbusdesk.booking.common.exception;

public class RoomNotAvailableException extends RuntimeException {

    public RoomNotAvailableException(Long roomId) {
        super("Room " + roomId + " is not available for the requested time range");
    }
}
