package com.parkrabbit.backend.repository;

import com.parkrabbit.backend.entity.ParkingSession;
import com.parkrabbit.backend.entity.ParkingSessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {

    boolean existsByReservationIdAndStatus(
            Long reservationId,
            ParkingSessionStatus status
    );
}
