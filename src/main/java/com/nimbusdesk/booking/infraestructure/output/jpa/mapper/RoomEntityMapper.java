package com.nimbusdesk.booking.infraestructure.output.jpa.mapper;

import com.nimbusdesk.booking.domain.model.Room;
import com.nimbusdesk.booking.infraestructure.output.jpa.entity.RoomEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomEntityMapper {

    Room toDomain(RoomEntity entity);
}
