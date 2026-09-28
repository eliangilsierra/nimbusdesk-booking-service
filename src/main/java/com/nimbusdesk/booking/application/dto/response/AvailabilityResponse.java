package com.nimbusdesk.booking.application.dto.response;

public record AvailabilityResponse(
        Long roomId,
        boolean available
) {
}
