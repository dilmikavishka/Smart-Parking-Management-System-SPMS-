package lk.ijse.userservice.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserAPI {

    @RequestMapping("/get")
    public String getUser() {
        return "User Service";
    }


}
