package Lab4.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "driver")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Driver {
    @Id
    @Column(name = "driverName", updatable = false, nullable = false)
    @Getter
    @Setter
    private String driverName;

    @Column(name = "driverTelephoneNumber")
    @Getter
    @Setter
    private String driverTelephoneNumber;
}
