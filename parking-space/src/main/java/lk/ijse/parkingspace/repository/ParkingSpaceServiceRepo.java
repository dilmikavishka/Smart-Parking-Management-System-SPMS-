package lk.ijse.parkingspace.repository;

import lk.ijse.parkingspace.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpaceServiceRepo  extends JpaRepository<ParkingSpace, String> {
    Optional<ParkingSpace> findByIdAndAvailableTrueAndReservedFalse(String id);

}
