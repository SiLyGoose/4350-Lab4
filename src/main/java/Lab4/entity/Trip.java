package Lab4.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trip")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Trip {
    @Id
    @Column(name = "tripNumber", updatable = false, nullable = false)
    @Getter
    @Setter
    private int tripNumber;

    @Column(name = "startLocationName")
    @Getter
    @Setter
    private String startLocationName;

    @Column(name = "destinationName")
    @Getter
    @Setter
    private String destinationName;
}
