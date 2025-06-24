package lk.ijse.paymentservice.services.paymentServiceImpl;


import lk.ijse.paymentservice.services.UserServiceCilent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserServiceClientImpl implements UserServiceCilent {
        private static final Logger logger = LoggerFactory.getLogger(UserServiceClientImpl.class);
    private final RestTemplate restTemplate;

    @Override
    public boolean isExistUser(String id) {
        try {
            String url = "http://user-service/api/v1/user/userExists/" + id;
            Boolean userExists = restTemplate.getForObject(url, Boolean.class);
            logger.info("User Exists: {}", userExists);
            return userExists != null && userExists;
        } catch (Exception e) {
            logger.error("Error checking if user exists", e);
        }
        return false;
    }
}
