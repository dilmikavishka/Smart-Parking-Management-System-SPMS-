package lk.ijse.paymentservice.services;

import lk.ijse.paymentservice.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {

    PaymentDTO proceedPayment(PaymentDTO paymentDTO);

    PaymentDTO updatePayment(PaymentDTO paymentDTO);

    PaymentDTO getPaymentById(String paymentId);

    List<PaymentDTO> getAllPayments();
}
