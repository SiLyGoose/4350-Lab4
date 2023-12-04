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
@Table(name = "actualTripStopInfo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@IdClass(ActualTripStopInfo.IdClass.class)
public class ActualTripStopInfo {
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

    @Id
    @Column(name = "stopNumber")
    @Getter
    @Setter
    private int stopNumber;

    @Column(name = "scheduledArrivalTime")
    @Getter
    @Setter
    private Time scheduledArrivalTime;

    @Column(name = "actualStartTime")
    @Getter
    @Setter
    private Time actualStartTime;

    @Column(name = "actualArrivalTime")
    @Getter
    @Setter
    private Time actualArrivalTime;

    @Column(name = "numberOfPassengerIn")
    @Getter
    @Setter
    private int numberOfPassengerIn;

    @Column(name = "numberOfPassengerOut")
    @Getter
    @Setter
    private int numberOfPassengerOut;

    public static class IdClass {
        private int tripNumber;
        private Date date;
        private Time scheduledStartTime;
        private int stopNumber;
    }
}
