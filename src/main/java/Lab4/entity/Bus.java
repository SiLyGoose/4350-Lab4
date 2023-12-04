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
@Table(name = "bus")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bus {
    @Id
    @Column(name = "busId", updatable = false, nullable = false)
    @Getter
    @Setter
    private int busId;

    @Column(name = "model")
    @Getter
    @Setter
    private String model;

    @Column(name = "year")
    @Getter
    @Setter
    private int year;
}
