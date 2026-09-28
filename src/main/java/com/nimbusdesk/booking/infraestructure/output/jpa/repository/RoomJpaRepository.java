package com.nimbusdesk.booking.infraestructure.output.jpa.repository;

import com.nimbusdesk.booking.infraestructure.output.jpa.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomJpaRepository extends JpaRepository<RoomEntity, Long> {
}
