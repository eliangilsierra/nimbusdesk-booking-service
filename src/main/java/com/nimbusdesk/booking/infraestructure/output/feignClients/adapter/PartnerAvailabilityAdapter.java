package com.nimbusdesk.booking.infraestructure.output.feignClients.adapter;

import com.nimbusdesk.booking.domain.spi.PartnerAvailabilityPort;
import com.nimbusdesk.booking.infraestructure.output.feignClients.PartnerBridgeFeignClient;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 * Implements the {@code PartnerAvailabilityPort} output port with a synchronous
 * Feign call to nimbusdesk-partner-bridge-service. If that call fails, availability
 * defaults to false: with no circuit breaker in this v1 (see nimbusdesk-infra
 * ROADMAP.md, "v1 vs v2"), a downstream outage here means "no bookings", not a
 * crash — a deliberately conservative fallback until Resilience4j lands in v2.
 */
@Component
public class PartnerAvailabilityAdapter implements PartnerAvailabilityPort {

    private final PartnerBridgeFeignClient partnerBridgeFeignClient;

    public PartnerAvailabilityAdapter(PartnerBridgeFeignClient partnerBridgeFeignClient) {
        this.partnerBridgeFeignClient = partnerBridgeFeignClient;
    }

    @Override
    public boolean isAvailable(Long roomId, LocalDateTime start, LocalDateTime end) {
        try {
            var response = partnerBridgeFeignClient.checkAvailability(roomId, start.toString(), end.toString());
            return response.available();
        } catch (Exception e) {
            return false;
        }
    }
}
