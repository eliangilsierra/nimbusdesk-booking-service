package com.nimbusdesk.booking.infraestructure.output.feignClients.dto;

public record PartnerAvailabilityResponseDto(
        Long roomId,
        boolean available
) {
}
