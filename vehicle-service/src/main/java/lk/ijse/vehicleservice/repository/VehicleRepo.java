package lk.ijse.vehicleservice.repository;

import lk.ijse.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle,String> {
}
