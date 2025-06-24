package lk.ijse.parkingspace.services.parkingServiceImpl;

import lk.ijse.parkingspace.convertion.ConvertionData;
import lk.ijse.parkingspace.dto.ParkingSpaceDTO;
import lk.ijse.parkingspace.entity.ParkingSpace;
import lk.ijse.parkingspace.repository.ParkingSpaceServiceRepo;
import lk.ijse.parkingspace.services.ParkingSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    @Autowired
    private ParkingSpaceServiceRepo parkingSpaceServiceRepo;

    @Autowired
    private ConvertionData convertionData;

    @Override
    public void saveParkingSpace(ParkingSpaceDTO parkingSpaceDTO) {
        parkingSpaceServiceRepo.save(convertionData.mapTo(parkingSpaceDTO, ParkingSpace.class));
    }

    @Override
    public void updateParkingSpace(ParkingSpaceDTO parkingSpaceDTO) {
        if (!parkingSpaceServiceRepo.existsById(parkingSpaceDTO.getId())) {
            return;
        }
        parkingSpaceServiceRepo.save(convertionData.mapTo(parkingSpaceDTO, ParkingSpace.class));
    }

    @Override
    public ParkingSpaceDTO getParkingSpace(String id) {
        if (parkingSpaceServiceRepo.existsById(id)) {
            lk.ijse.parkingspace.entity.ParkingSpace parkingSpace = parkingSpaceServiceRepo.findById(id).orElse(null);
            return convertionData.mapTo(parkingSpace, ParkingSpaceDTO.class);
        }
        return null;
    }

    @Override
    public void deleteParkingSpace(String id) {
        if (parkingSpaceServiceRepo.existsById(id)) {
            parkingSpaceServiceRepo.deleteById(id);
        }
    }

    @Override
    public List<ParkingSpaceDTO> getAllParkingSpaces() {
        return convertionData.mapTo(parkingSpaceServiceRepo.findAll(), ParkingSpaceDTO.class);
    }

    @Override
    public boolean isParkingSpaceAvailableAndNotReserved(String parkingSpaceId) {
        return parkingSpaceServiceRepo
                .findByIdAndAvailableTrueAndReservedFalse(parkingSpaceId)
                .isPresent();
    }



}
