package lk.ijse.paymentservice.repository;

import lk.ijse.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,String> {
    boolean findAllByVehicleId(String vehicleId);

    boolean findAllByUserId(String userId);
}
