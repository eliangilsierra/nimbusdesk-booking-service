package com.nimbusdesk.booking.domain.useCase;

import com.nimbusdesk.booking.common.exception.BookingNotFoundException;
import com.nimbusdesk.booking.common.exception.RoomNotAvailableException;
import com.nimbusdesk.booking.common.exception.RoomNotFoundException;
import com.nimbusdesk.booking.domain.api.BookingServicePort;
import com.nimbusdesk.booking.domain.enums.BookingStatus;
import com.nimbusdesk.booking.domain.model.Booking;
import com.nimbusdesk.booking.domain.model.Room;
import com.nimbusdesk.booking.domain.spi.BookingPersistencePort;
import com.nimbusdesk.booking.domain.spi.PartnerAvailabilityPort;
import com.nimbusdesk.booking.domain.spi.RoomPersistencePort;
import com.nimbusdesk.booking.domain.util.PricingCalculator;
import java.time.LocalDateTime;

/**
 * Orchestrates the booking use cases. Depends only on ports ({@code domain.api},
 * {@code domain.spi}) — it never references a JPA repository, a Feign client or a
 * DTO directly. Swapping MySQL for anything else, or the partner integration for a
 * different protocol, never touches this class.
 */
public class BookingUseCase implements BookingServicePort {

    private final RoomPersistencePort roomPersistencePort;
    private final BookingPersistencePort bookingPersistencePort;
    private final PartnerAvailabilityPort partnerAvailabilityPort;

    public BookingUseCase(
            RoomPersistencePort roomPersistencePort,
            BookingPersistencePort bookingPersistencePort,
            PartnerAvailabilityPort partnerAvailabilityPort) {
        this.roomPersistencePort = roomPersistencePort;
        this.bookingPersistencePort = bookingPersistencePort;
        this.partnerAvailabilityPort = partnerAvailabilityPort;
    }

    @Override
    public Booking createBooking(Long roomId, String requestedBy, LocalDateTime start, LocalDateTime end) {
        Room room = roomPersistencePort.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException(roomId));

        if (!checkAvailability(roomId, start, end)) {
            throw new RoomNotAvailableException(roomId);
        }

        Booking booking = Booking.builder()
                .roomId(roomId)
                .requestedBy(requestedBy)
                .startTime(start)
                .endTime(end)
                .totalPrice(PricingCalculator.calculate(room, start, end))
                .status(BookingStatus.CONFIRMED)
                .build();

        return bookingPersistencePort.save(booking);
    }

    @Override
    public Booking cancelBooking(Long bookingId) {
        Booking booking = getBooking(bookingId);
        booking.setStatus(BookingStatus.CANCELLED);
        return bookingPersistencePort.save(booking);
    }

    @Override
    public Booking getBooking(Long bookingId) {
        return bookingPersistencePort.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(bookingId));
    }

    @Override
    public boolean checkAvailability(Long roomId, LocalDateTime start, LocalDateTime end) {
        boolean freeLocally = bookingPersistencePort.findActiveByRoomAndRange(roomId, start, end).isEmpty();
        return freeLocally && partnerAvailabilityPort.isAvailable(roomId, start, end);
    }
}
