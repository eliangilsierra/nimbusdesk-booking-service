package com.nimbusdesk.booking.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookingResponse(
        Long id,
        Long roomId,
        String requestedBy,
        LocalDateTime startTime,
        LocalDateTime endTime,
        BigDecimal totalPrice,
        String status
) {
}
