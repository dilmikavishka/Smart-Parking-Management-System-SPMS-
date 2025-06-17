package lk.ijse.userservice.services.userServiceImpl;

import lk.ijse.userservice.convertion.ConvertionData;
import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.User;
import lk.ijse.userservice.repository.UserRepo;
import lk.ijse.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private ConvertionData convertionData;

    @Override
    public void saveUser(UserDTO userDTO) {
       userRepo.save(convertionData.mapTo(userDTO, User.class));

    }

    @Override
    public List<UserDTO> getAllUsers() {
        return convertionData.mapTo(userRepo.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO getUser(String userId) {
    if (userRepo.existsById(userId)) {
            User user = userRepo.findById(userId).orElse(null);
            return convertionData.mapTo(user, UserDTO.class);
        }
        return null;
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if (!userRepo.existsById(userDTO.getUserId())) {
            return;
        }
        userRepo.save(convertionData.mapTo(userDTO, User.class));
    }

    @Override
    public void deleteUser(String userId) {
        userRepo.deleteById(userId);


    }

    @Override
    public boolean isUserExists(String userId) {
        return userRepo.existsById(userId);
    }


}
