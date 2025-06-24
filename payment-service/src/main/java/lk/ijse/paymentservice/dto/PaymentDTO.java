package lk.ijse.paymentservice.dto;

import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class PaymentDTO {
    private String paymentId;
    private String userId;
    private String vehicleId;
    private String parkingSpotId;
    private double amount;
    private LocalDateTime time;
    private String status;
}
