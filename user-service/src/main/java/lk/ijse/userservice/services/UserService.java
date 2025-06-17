package lk.ijse.userservice.services;

import lk.ijse.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();

    UserDTO getUser(String userId);


    void updateUser(UserDTO userDTO);

    void deleteUser(String userId);

    boolean isUserExists(String userId);
}
