package com.nimbusdesk.booking.infraestructure.configuration;

import com.nimbusdesk.booking.domain.api.BookingServicePort;
import com.nimbusdesk.booking.domain.spi.BookingPersistencePort;
import com.nimbusdesk.booking.domain.spi.PartnerAvailabilityPort;
import com.nimbusdesk.booking.domain.spi.RoomPersistencePort;
import com.nimbusdesk.booking.domain.useCase.BookingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Wires the domain use case with its concrete adapters. This is the only place
 * where {@code domain.useCase} is instantiated with Spring-managed beans — the
 * domain package itself stays free of framework annotations.
 */
@Configuration
public class UseCasesConfig {

    @Bean
    public BookingServicePort bookingServicePort(
            RoomPersistencePort roomPersistencePort,
            BookingPersistencePort bookingPersistencePort,
            PartnerAvailabilityPort partnerAvailabilityPort) {
        return new BookingUseCase(roomPersistencePort, bookingPersistencePort, partnerAvailabilityPort);
    }
}
