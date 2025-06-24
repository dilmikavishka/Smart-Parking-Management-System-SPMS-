package lk.ijse.vehicleservice.services.vehicleServiceImpl;

import lk.ijse.vehicleservice.convertion.ConvertionData;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.repository.VehicleRepo;
import lk.ijse.vehicleservice.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl  implements VehicleService {
    // Implement vehicle-related CRUD operations here
    @Autowired
    ConvertionData convertionData;

    @Autowired
    VehicleRepo vehicleRepository;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        vehicleRepository.save(convertionData.mapTo(vehicleDTO, Vehicle.class));
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO) {
        if(!vehicleRepository.existsById(vehicleDTO.getVehicleId())){
            return;
        }
        vehicleRepository.save(convertionData.mapTo(vehicleDTO, Vehicle.class));
    }

    @Override
    public VehicleDTO getVehicle(String vehicleId) {
        if (!vehicleRepository.existsById(vehicleId)) {
            return null;
        }
        return convertionData.mapTo(vehicleRepository.findById(vehicleId).get(), VehicleDTO.class);
    }

    @Override
    public void deleteVehicle(String vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return convertionData.mapTo(vehicleRepository.findAll(), VehicleDTO.class);
    }

    @Override
    public List<VehicleDTO> getVehicleByUserId(String userId) {
        return convertionData.mapTo(vehicleRepository.findAllByUserId(userId), VehicleDTO.class);
    }


}