package com.parkrabbit.backend.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.parkrabbit.backend.entity.ParkingSlot;
import com.parkrabbit.backend.entity.enums.ParkingSlotStatus;




public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long>{
    Optional<ParkingSlot> findFirstByParkingLotIdAndStatus(
        Long parkingLotId,
        ParkingSlotStatus status
    );
}
