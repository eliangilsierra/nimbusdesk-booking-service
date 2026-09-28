package com.nimbusdesk.booking.domain.spi;

import com.nimbusdesk.booking.domain.model.Booking;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingPersistencePort {

    Booking save(Booking booking);

    Optional<Booking> findById(Long bookingId);

    List<Booking> findActiveByRoomAndRange(Long roomId, LocalDateTime start, LocalDateTime end);
}
