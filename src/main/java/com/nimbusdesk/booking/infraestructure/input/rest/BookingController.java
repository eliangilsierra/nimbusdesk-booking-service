package com.nimbusdesk.booking.infraestructure.input.rest;

import com.nimbusdesk.booking.application.dto.request.CreateBookingRequest;
import com.nimbusdesk.booking.application.dto.response.AvailabilityResponse;
import com.nimbusdesk.booking.application.dto.response.BookingResponse;
import com.nimbusdesk.booking.application.handler.BookingHandler;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingHandler bookingHandler;

    public BookingController(BookingHandler bookingHandler) {
        this.bookingHandler = bookingHandler;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody CreateBookingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingHandler.createBooking(request));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingHandler.getBooking(bookingId));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> cancelBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingHandler.cancelBooking(bookingId));
    }

    @GetMapping("/availability")
    public ResponseEntity<AvailabilityResponse> checkAvailability(
            @RequestParam Long roomId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(bookingHandler.checkAvailability(roomId, start, end));
    }
}
