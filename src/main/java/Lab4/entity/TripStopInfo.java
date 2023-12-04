package Lab4.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tripStopInfo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@IdClass(TripStopInfo.IdClass.class)
public class TripStopInfo {
    @Id
    @Column(name = "tripNumber", updatable = false, nullable = false)
    @Getter
    @Setter
    private int tripNumber;

    @Id
    @Column(name = "stopNumber")
    @Getter
    @Setter
    private int stopNumber;

    @Column(name = "sequenceNumber")
    @Getter
    @Setter
    private int sequenceNumber;

    @Column(name = "drivingTime")
    @Getter
    @Setter
    private Time drivingTime;

    public static class IdClass {
        private int tripNumber;
        private int stopNumber;
    }
}
