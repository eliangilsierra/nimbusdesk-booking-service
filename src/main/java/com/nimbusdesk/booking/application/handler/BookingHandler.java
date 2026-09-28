package com.nimbusdesk.booking.application.handler;

import com.nimbusdesk.booking.application.dto.request.CreateBookingRequest;
import com.nimbusdesk.booking.application.dto.response.AvailabilityResponse;
import com.nimbusdesk.booking.application.dto.response.BookingResponse;
import java.time.LocalDateTime;

/**
 * Sits between the REST controller and the domain port: translates DTOs to/from
 * domain calls. Controllers never touch {@code domain.api} directly.
 */
public interface BookingHandler {

    BookingResponse createBooking(CreateBookingRequest request);

    BookingResponse cancelBooking(Long bookingId);

    BookingResponse getBooking(Long bookingId);

    AvailabilityResponse checkAvailability(Long roomId, LocalDateTime start, LocalDateTime end);
}
