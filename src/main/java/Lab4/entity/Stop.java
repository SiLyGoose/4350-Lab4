package Lab4.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stop")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Stop {
    @Id
    @Column(name = "stopNumber", updatable = false, nullable = false)
    @Getter
    @Setter
    private int stopNumber;

    @Column(name = "stopAddress")
    @Getter
    @Setter
    private String stopAddress;
}
