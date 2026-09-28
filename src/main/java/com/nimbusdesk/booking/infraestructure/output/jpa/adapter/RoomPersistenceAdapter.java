package com.nimbusdesk.booking.infraestructure.output.jpa.adapter;

import com.nimbusdesk.booking.domain.model.Room;
import com.nimbusdesk.booking.domain.spi.RoomPersistencePort;
import com.nimbusdesk.booking.infraestructure.output.jpa.mapper.RoomEntityMapper;
import com.nimbusdesk.booking.infraestructure.output.jpa.repository.RoomJpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class RoomPersistenceAdapter implements RoomPersistencePort {

    private final RoomJpaRepository roomJpaRepository;
    private final RoomEntityMapper roomEntityMapper;

    public RoomPersistenceAdapter(RoomJpaRepository roomJpaRepository, RoomEntityMapper roomEntityMapper) {
        this.roomJpaRepository = roomJpaRepository;
        this.roomEntityMapper = roomEntityMapper;
    }

    @Override
    public Optional<Room> findById(Long roomId) {
        return roomJpaRepository.findById(roomId).map(roomEntityMapper::toDomain);
    }
}
