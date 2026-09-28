package com.nimbusdesk.booking.infraestructure.output.jpa.repository;

import com.nimbusdesk.booking.domain.enums.BookingStatus;
import com.nimbusdesk.booking.infraestructure.output.jpa.entity.BookingEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingJpaRepository extends JpaRepository<BookingEntity, Long> {

    @Query("""
            SELECT b FROM BookingEntity b
            WHERE b.roomId = :roomId
              AND b.status = :status
              AND b.startTime < :end
              AND b.endTime > :start
            """)
    List<BookingEntity> findOverlapping(
            @Param("roomId") Long roomId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("status") BookingStatus status);
}
