package com.nimbusdesk.booking.application.handler.impl;

import com.nimbusdesk.booking.application.dto.request.CreateBookingRequest;
import com.nimbusdesk.booking.application.dto.response.AvailabilityResponse;
import com.nimbusdesk.booking.application.dto.response.BookingResponse;
import com.nimbusdesk.booking.application.handler.BookingHandler;
import com.nimbusdesk.booking.application.mapper.response.BookingResponseMapper;
import com.nimbusdesk.booking.domain.api.BookingServicePort;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class BookingHandlerImpl implements BookingHandler {

    private final BookingServicePort bookingServicePort;
    private final BookingResponseMapper bookingResponseMapper;

    public BookingHandlerImpl(BookingServicePort bookingServicePort, BookingResponseMapper bookingResponseMapper) {
        this.bookingServicePort = bookingServicePort;
        this.bookingResponseMapper = bookingResponseMapper;
    }

    @Override
    public BookingResponse createBooking(CreateBookingRequest request) {
        var booking = bookingServicePort.createBooking(
                request.roomId(), request.requestedBy(), request.startTime(), request.endTime());
        return bookingResponseMapper.toResponse(booking);
    }

    @Override
    public BookingResponse cancelBooking(Long bookingId) {
        return bookingResponseMapper.toResponse(bookingServicePort.cancelBooking(bookingId));
    }

    @Override
    public BookingResponse getBooking(Long bookingId) {
        return bookingResponseMapper.toResponse(bookingServicePort.getBooking(bookingId));
    }

    @Override
    public AvailabilityResponse checkAvailability(Long roomId, LocalDateTime start, LocalDateTime end) {
        boolean available = bookingServicePort.checkAvailability(roomId, start, end);
        return new AvailabilityResponse(roomId, available);
    }
}
