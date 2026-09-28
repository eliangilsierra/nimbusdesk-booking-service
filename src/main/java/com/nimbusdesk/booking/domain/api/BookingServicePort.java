package com.nimbusdesk.booking.domain.api;

import com.nimbusdesk.booking.domain.model.Booking;
import java.time.LocalDateTime;

/**
 * Input port: the use cases this service exposes, independent of REST/DTOs.
 */
public interface BookingServicePort {

    Booking createBooking(Long roomId, String requestedBy, LocalDateTime start, LocalDateTime end);

    Booking cancelBooking(Long bookingId);

    Booking getBooking(Long bookingId);

    boolean checkAvailability(Long roomId, LocalDateTime start, LocalDateTime end);
}
