package lk.ijse.parkingspace.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "parking_space")
public class ParkingSpace {
    @Id
    private String id;
    private String location;
    private String zone;
    private boolean available;
    private boolean reserved;
    private String ownerId;
}

