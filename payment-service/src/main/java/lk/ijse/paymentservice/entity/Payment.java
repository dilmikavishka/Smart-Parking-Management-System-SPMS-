package lk.ijse.paymentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payment {
    @Id
    private String paymentId;
    private String userId;
    private String vehicleId;
    private String parkingSpotId;
    private double amount;
    private LocalDateTime time;
    private String status;
}