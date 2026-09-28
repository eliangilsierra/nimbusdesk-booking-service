package com.nimbusdesk.booking.domain.util;

import com.nimbusdesk.booking.domain.model.Room;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Pure domain logic, no Spring, no persistence: price = hourly rate * duration.
 */
public final class PricingCalculator {

    private PricingCalculator() {
    }

    public static BigDecimal calculate(Room room, LocalDateTime start, LocalDateTime end) {
        long minutes = Duration.between(start, end).toMinutes();
        BigDecimal hours = BigDecimal.valueOf(minutes).divide(BigDecimal.valueOf(60), 4, RoundingMode.HALF_UP);
        return room.getPricePerHour().multiply(hours).setScale(2, RoundingMode.HALF_UP);
    }
}
