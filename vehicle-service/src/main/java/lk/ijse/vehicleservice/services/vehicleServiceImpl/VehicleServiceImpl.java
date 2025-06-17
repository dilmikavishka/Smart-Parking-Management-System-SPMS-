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

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    ConvertionData convertionData;

    @Autowired
    VehicleRepo vehicleRepository;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        vehicleRepository.save(convertionData.mapTo(vehicleDTO, Vehicle.class));

    }
}
