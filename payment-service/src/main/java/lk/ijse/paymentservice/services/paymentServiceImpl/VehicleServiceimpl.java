package lk.ijse.paymentservice.services.paymentServiceImpl;

import lk.ijse.paymentservice.services.UserServiceCilent;
import lk.ijse.paymentservice.services.VehicleServiceClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class VehicleServiceimpl implements VehicleServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceimpl.class);
    private final RestTemplate restTemplate;

    @Override
    public boolean isExistVehicle(String id) {
        try {
            String url = "http://vehicle-service/api/v1/vehicle/Exists/" + id;
            Boolean vehicleExists = restTemplate.getForObject(url, Boolean.class);
            logger.info("Vehicle Exists: {}", vehicleExists);
            return vehicleExists != null && vehicleExists;
        } catch (Exception e) {
            logger.error("Error checking if vehicle exists", e);
        }
        return false;
    }
}
