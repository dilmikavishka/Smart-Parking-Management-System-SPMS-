package lk.ijse.parkingspace.services;

import lk.ijse.parkingspace.dto.ParkingSpaceDTO;

import java.util.List;

public interface ParkingSpaceService {
    void saveParkingSpace(ParkingSpaceDTO parkingSpaceDTO);

    void updateParkingSpace(ParkingSpaceDTO parkingSpaceDTO);

    ParkingSpaceDTO getParkingSpace(String id);

    void deleteParkingSpace(String id);

    List<ParkingSpaceDTO> getAllParkingSpaces();

    boolean isParkingSpaceAvailableAndNotReserved(String parkingSpaceId);

}
