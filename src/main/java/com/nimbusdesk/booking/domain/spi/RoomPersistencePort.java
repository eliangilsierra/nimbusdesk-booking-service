package com.nimbusdesk.booking.domain.spi;

import com.nimbusdesk.booking.domain.model.Room;
import java.util.Optional;

/**
 * Output port: how the domain reads room data. The domain does not know (or
 * care) that it is currently backed by JPA/MySQL.
 */
public interface RoomPersistencePort {

    Optional<Room> findById(Long roomId);
}
