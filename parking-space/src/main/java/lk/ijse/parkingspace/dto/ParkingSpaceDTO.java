package lk.ijse.parkingspace.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class ParkingSpaceDTO {
        private String id;
        private String location;
        private String zone;
        private boolean available;
        private boolean reserved;
        private String ownerId;

}
