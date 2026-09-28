package com.nimbusdesk.booking.domain.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.nimbusdesk.booking.domain.model.Room;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * No Spring context needed: this is the whole point of keeping pricing logic in
 * {@code domain}, framework-free.
 */
class PricingCalculatorTest {

    @Test
    void calculatesPriceForTwoHours() {
        Room room = Room.builder().pricePerHour(BigDecimal.valueOf(10)).build();
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 1, 11, 0);

        BigDecimal price = PricingCalculator.calculate(room, start, end);

        assertThat(price).isEqualByComparingTo("20.00");
    }

    @Test
    void calculatesPriceForPartialHour() {
        Room room = Room.builder().pricePerHour(BigDecimal.valueOf(12)).build();
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 1, 9, 30);

        BigDecimal price = PricingCalculator.calculate(room, start, end);

        assertThat(price).isEqualByComparingTo("6.00");
    }
}
