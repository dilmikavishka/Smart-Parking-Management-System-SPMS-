package lk.ijse.userservice.api;


import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserAPI {

    @Autowired
    private UserService USER_SERVICE;

    @RequestMapping("/get")
    public String getUser() {
        return "User Service";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO , BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        USER_SERVICE.saveUser(userDTO);
        return ResponseEntity.ok("User saved successfully");
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(USER_SERVICE.getAllUsers());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO , BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        USER_SERVICE.updateUser(userDTO);
        return ResponseEntity.ok("User updated successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") String userId) {
        UserDTO user = USER_SERVICE.getUser(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
       try {
              USER_SERVICE.deleteUser(userId);
              return ResponseEntity.ok("User deleted successfully");
         } catch (Exception e) {
              return ResponseEntity.status(500).body("Error deleting user: " + e.getMessage());
       }

    }

    @GetMapping("/userExists/{userId}")
    public ResponseEntity<?> isUserExists(@PathVariable String userId) {
      try {
          boolean isUserExists = USER_SERVICE.isUserExists(userId);
          return ResponseEntity.ok(isUserExists);
      }catch (Exception exception) {
          return ResponseEntity.status(500).body("Error checking user existence: " + exception.getMessage());
      }
    }



}



/*
* BindingResult → validation error ekak thinawada kiyala check karanawa
*
* */
