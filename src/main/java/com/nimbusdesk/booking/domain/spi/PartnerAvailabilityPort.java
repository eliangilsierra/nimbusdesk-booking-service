package com.nimbusdesk.booking.domain.spi;

import java.time.LocalDateTime;

/**
 * Output port toward nimbusdesk-partner-bridge-service. The domain only knows it
 * needs to ask "is this room clear on the partner side too?" — it has no idea the
 * answer travels over Feign/HTTP to another microservice.
 */
public interface PartnerAvailabilityPort {

    boolean isAvailable(Long roomId, LocalDateTime start, LocalDateTime end);
}
