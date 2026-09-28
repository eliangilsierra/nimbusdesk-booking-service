package com.nimbusdesk.booking.infraestructure.output.jpa.mapper;

import com.nimbusdesk.booking.domain.model.Booking;
import com.nimbusdesk.booking.infraestructure.output.jpa.entity.BookingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingEntityMapper {

    BookingEntity toEntity(Booking booking);

    Booking toDomain(BookingEntity entity);
}
