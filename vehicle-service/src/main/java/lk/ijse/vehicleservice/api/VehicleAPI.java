package lk.ijse.vehicleservice.api;

import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.services.UserServiceCilent;
import lk.ijse.vehicleservice.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleAPI {

    @Autowired
    private UserServiceCilent userServiceCilent;

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
        if (!userServiceCilent.isExistUser(vehicleDTO.getUserId())) {
            return ResponseEntity.badRequest().body("User not found");
        }

        VEHICLE_SERVICE.saveVehicle(vehicleDTO);
        return ResponseEntity.ok("Vehicle saved successfully");
    }

    @GetMapping
    public ResponseEntity<?> getAllVehicles() {
        return ResponseEntity.ok(VEHICLE_SERVICE.getAllVehicles());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateVehicle(@RequestBody VehicleDTO vehicleDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        VEHICLE_SERVICE.updateVehicle(vehicleDTO);
        return ResponseEntity.ok("Vehicle updated successfully");
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<?> getVehicle(@PathVariable ("vehicleId") String vehicleId){
        try{
            return ResponseEntity.ok(VEHICLE_SERVICE.getVehicle(vehicleId));
        }catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Vehicle not found with ID: " + vehicleId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving vehicle: " + e.getMessage());
        }

    }

    @GetMapping("/Exists/{vehicleId}")
    public ResponseEntity<?> isExistVehicle(@PathVariable String vehicleId) {
        try {
            boolean exists = VEHICLE_SERVICE.getVehicle(vehicleId) != null;
            return ResponseEntity.ok(exists);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Vehicle not found with ID: " + vehicleId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error checking vehicle existence: " + e.getMessage());
        }
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable String vehicleId) {
        try {
            VEHICLE_SERVICE.deleteVehicle(vehicleId);
            return ResponseEntity.ok("Vehicle deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting vehicle: " + e.getMessage());
        }
    }




}
