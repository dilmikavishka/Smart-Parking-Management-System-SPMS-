package lk.ijse.vehicleservice.api;

import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleAPI {

    @Autowired
    private VehicleService VEHICLE_SERVICE;

    @RequestMapping("/get")
    public String helloVehicle() {
        return "Hello, Vehicle!";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveVehicle(@RequestBody VehicleDTO vehicleDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
       /* if (!userServiceCilent.isExistUser(vehicleDTO.getUserId())) {
            return ResponseEntity.badRequest().body("User not found");
        }*/

        VEHICLE_SERVICE.saveVehicle(vehicleDTO);
        return ResponseEntity.ok("Vehicle saved successfully");
    }

}
