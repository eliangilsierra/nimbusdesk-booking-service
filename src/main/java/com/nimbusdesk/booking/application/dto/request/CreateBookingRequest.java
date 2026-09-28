package com.nimbusdesk.booking.application.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CreateBookingRequest(

        @NotNull
        Long roomId,

        @NotBlank
        String requestedBy,

        @NotNull @Future
        LocalDateTime startTime,

        @NotNull @Future
        LocalDateTime endTime

) {
}
