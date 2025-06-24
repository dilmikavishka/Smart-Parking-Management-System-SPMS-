package lk.ijse.paymentservice.services.paymentServiceImpl;

import lk.ijse.paymentservice.convertion.ConvertionData;
import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.entity.Payment;
import lk.ijse.paymentservice.repository.PaymentRepo;
import lk.ijse.paymentservice.services.PaymentService;
import lk.ijse.paymentservice.services.UserServiceCilent;
import lk.ijse.paymentservice.services.VehicleServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
     private final UserServiceCilent userServiceCilent;
     private final VehicleServiceClient vehicleServiceClient;
     private final ConvertionData convertionData;
     private final PaymentRepo paymentRepo;


    @Override
    public PaymentDTO proceedPayment(PaymentDTO paymentDTO) {
        if (!userServiceCilent.isExistUser(paymentDTO.getUserId())) {
            throw new RuntimeException("User does not exist");
        }
        if (!vehicleServiceClient.isExistVehicle(paymentDTO.getVehicleId())) {
            throw new RuntimeException("Vehicle does not exist");
        }

        Payment saved = paymentRepo.save(convertionData.mapTo(paymentDTO, Payment.class));
        return convertionData.mapTo(saved, PaymentDTO.class);
    }

    @Override
    public PaymentDTO updatePayment(PaymentDTO paymentDTO) {
        if (!userServiceCilent.isExistUser(paymentDTO.getUserId())) {
            throw new RuntimeException("User does not exist");
        }
        if (!vehicleServiceClient.isExistVehicle(paymentDTO.getVehicleId())) {
            throw new RuntimeException("Vehicle does not exist");
        }

        if (!paymentRepo.existsById(paymentDTO.getPaymentId())) {
            throw new RuntimeException("Payment does not exist");
        }

        Payment updated = paymentRepo.save(convertionData.mapTo(paymentDTO, Payment.class));
        return convertionData.mapTo(updated, PaymentDTO.class);
    }

    @Override
    public PaymentDTO getPaymentById(String paymentId) {
        if (!paymentRepo.existsById(paymentId)) {
            return null;
        }
        Payment payment = paymentRepo.findById(paymentId).orElse(null);
        return convertionData.mapTo(payment, PaymentDTO.class);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepo.findAll();
        return convertionData.mapTo(payments, PaymentDTO.class);
    }


}
