package Lab4.repository;

import Lab4.entity.TripOffering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripOfferingRepository extends JpaRepository<TripOffering, TripOffering.IdClass> {

}
