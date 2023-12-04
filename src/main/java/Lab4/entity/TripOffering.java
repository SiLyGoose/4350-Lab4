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
@Table(name = "tripOffering")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@IdClass(TripOffering.IdClass.class)
public class TripOffering {
    @Id
    @Column(name = "tripNumber", updatable = false, nullable = false)
    @Getter
    @Setter
    private int tripNumber;

    @Id
    @Column(name = "date")
    @Getter
    @Setter
    private Date date;

    @Id
    @Column(name = "scheduledStartTime")
    @Getter
    @Setter
    private Time scheduledStartTime;

    @Column(name = "scheduledArrivalTime")
    @Getter
    @Setter
    private Time scheduledArrivalTime;

    @Column(name = "driverName")
    @Getter
    @Setter
    private String driverName;

    @Column(name = "busId")
    @Getter
    @Setter
    private int busId;

    public static class IdClass {
        private int tripNumber;
        private Date date;
        private Time scheduledStartTime;
    }
}
