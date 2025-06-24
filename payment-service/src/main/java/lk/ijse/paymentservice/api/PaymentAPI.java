package lk.ijse.paymentservice.api;

import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.services.ParkingSpaceService;
import lk.ijse.paymentservice.services.PaymentService;
import lk.ijse.paymentservice.services.UserServiceCilent;
import lk.ijse.paymentservice.services.VehicleServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentAPI {
    @Autowired
    private UserServiceCilent userServiceCilent;

    @Autowired
    private VehicleServiceClient vehicleServiceClient;

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/health")
    public String helloPayment() {
        return "Hello, Payment!";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> proceedPayment(@RequestBody PaymentDTO paymentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (!userServiceCilent.isExistUser(paymentDTO.getUserId())) {
            return ResponseEntity.badRequest().body("User not found");
        }
        if (!vehicleServiceClient.isExistVehicle(paymentDTO.getVehicleId())) {
            System.out.println(paymentDTO.getVehicleId() + " not found");
            return ResponseEntity.badRequest().body("Vehicle not found");
        }
        if (!parkingSpaceService.isParkingSpaceAvailable(paymentDTO.getParkingSpotId()  )) {
            return ResponseEntity.badRequest().body("Parking space not available");
        }

        PaymentDTO receipt = paymentService.proceedPayment(paymentDTO);
        return ResponseEntity.ok(receipt);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePayment(@RequestBody PaymentDTO paymentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (!userServiceCilent.isExistUser(paymentDTO.getUserId())) {
            return ResponseEntity.badRequest().body("User not found");
        }
        if (!vehicleServiceClient.isExistVehicle(paymentDTO.getVehicleId())) {
            return ResponseEntity.badRequest().body("Vehicle not found");
        }



        PaymentDTO updatedPayment = paymentService.updatePayment(paymentDTO);
        return ResponseEntity.ok(updatedPayment);
    }

    @GetMapping(value = "/{paymentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPaymentById(@PathVariable String paymentId) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(paymentId);
        if (paymentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paymentDTO);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        if (payments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(payments);
    }


}
