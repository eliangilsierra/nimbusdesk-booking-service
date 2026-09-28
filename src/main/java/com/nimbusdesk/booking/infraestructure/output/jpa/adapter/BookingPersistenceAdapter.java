package com.nimbusdesk.booking.infraestructure.output.jpa.adapter;

import com.nimbusdesk.booking.domain.enums.BookingStatus;
import com.nimbusdesk.booking.domain.model.Booking;
import com.nimbusdesk.booking.domain.spi.BookingPersistencePort;
import com.nimbusdesk.booking.infraestructure.output.jpa.mapper.BookingEntityMapper;
import com.nimbusdesk.booking.infraestructure.output.jpa.repository.BookingJpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class BookingPersistenceAdapter implements BookingPersistencePort {

    private final BookingJpaRepository bookingJpaRepository;
    private final BookingEntityMapper bookingEntityMapper;

    public BookingPersistenceAdapter(
            BookingJpaRepository bookingJpaRepository, BookingEntityMapper bookingEntityMapper) {
        this.bookingJpaRepository = bookingJpaRepository;
        this.bookingEntityMapper = bookingEntityMapper;
    }

    @Override
    public Booking save(Booking booking) {
        var saved = bookingJpaRepository.save(bookingEntityMapper.toEntity(booking));
        return bookingEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Booking> findById(Long bookingId) {
        return bookingJpaRepository.findById(bookingId).map(bookingEntityMapper::toDomain);
    }

    @Override
    public List<Booking> findActiveByRoomAndRange(Long roomId, LocalDateTime start, LocalDateTime end) {
        return bookingJpaRepository.findOverlapping(roomId, start, end, BookingStatus.CONFIRMED).stream()
                .map(bookingEntityMapper::toDomain)
                .toList();
    }
}
