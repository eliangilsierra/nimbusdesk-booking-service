package com.nimbusdesk.booking.application.mapper.response;

import com.nimbusdesk.booking.application.dto.response.BookingResponse;
import com.nimbusdesk.booking.domain.model.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingResponseMapper {

    BookingResponse toResponse(Booking booking);
}
