package com.nimbusdesk.booking.common.exception;

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException(Long roomId) {
        super("Room not found: " + roomId);
    }
}
