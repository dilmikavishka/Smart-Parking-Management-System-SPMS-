package lk.ijse.vehicleservice.services;

import lk.ijse.vehicleservice.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehicles();

    void updateVehicle(VehicleDTO vehicleDTO);

    VehicleDTO getVehicle(String vehicleId);

    void deleteVehicle(String vehicleId);
    List<VehicleDTO> getVehicleByUserId(String userId);
}
