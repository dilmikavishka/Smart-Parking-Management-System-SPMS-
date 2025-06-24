package lk.ijse.parkingspace.api;

import lk.ijse.parkingspace.dto.ParkingSpaceDTO;
import lk.ijse.parkingspace.services.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking")
public class ParkingSpaceServiceAPI {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @RequestMapping("/get")
    public String helloVehicle() {
        return "Hello, Parking!";
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveSpace(@RequestBody ParkingSpaceDTO parkingSpaceDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        parkingSpaceService.saveParkingSpace(parkingSpaceDTO);
        return ResponseEntity.ok("Parking space saved successfully");
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSpace(@RequestBody ParkingSpaceDTO parkingSpaceDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        parkingSpaceService.updateParkingSpace(parkingSpaceDTO);
        return ResponseEntity.ok("Parking space  updated successfully");
    }

    @GetMapping(value = "/getBy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getParkingSpace(@RequestParam String id) {
        ParkingSpaceDTO parkingSpaceDTO = parkingSpaceService.getParkingSpace(id);
        if (parkingSpaceDTO != null) {
            return ResponseEntity.ok(parkingSpaceDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping(value = "/check-availability", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkAvailability(@RequestParam String id) {
        boolean isAvailable = parkingSpaceService.isParkingSpaceAvailableAndNotReserved(id);
        return ResponseEntity.ok(isAvailable);
    }


    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ParkingSpaceDTO>> getAllParkingSpaces() {
        return ResponseEntity.ok(parkingSpaceService.getAllParkingSpaces());
    }


    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteParkingSpace(@RequestParam String id) {
        if (parkingSpaceService.getParkingSpace(id) != null) {
            parkingSpaceService.deleteParkingSpace(id);
            return ResponseEntity.ok("Parking space deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}