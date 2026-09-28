package com.nimbusdesk.booking.domain.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A bookable space (meeting room / coworking desk). Plain domain model — no
 * persistence or framework annotations, on purpose: the domain does not know
 * JPA exists.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    private Long id;
    private String name;
    private Integer capacity;
    private BigDecimal pricePerHour;
}
