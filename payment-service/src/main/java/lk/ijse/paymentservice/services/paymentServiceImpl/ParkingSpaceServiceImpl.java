package lk.ijse.paymentservice.services.paymentServiceImpl;

import lk.ijse.paymentservice.services.ParkingSpaceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ParkingSpaceServiceImpl implements ParkingSpaceService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceClientImpl.class);
    private final RestTemplate restTemplate;

    @Override
    public boolean isParkingSpaceAvailable(String parkingSpaceId) {
        try {
            String url = "http://parking-space/api/v1/parking/check-availability?id=" + parkingSpaceId;
            Boolean userExists = restTemplate.getForObject(url, Boolean.class);
            logger.info("User Exists: {}", userExists);
            return userExists != null && userExists;
        } catch (Exception e) {
            logger.error("Error checking if user exists", e);
        }
        return false;
    }
}
