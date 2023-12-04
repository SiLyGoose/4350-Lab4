package Lab4.repository;

import Lab4.entity.TripStopInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripStopInfoRepository extends JpaRepository<TripStopInfo, TripStopInfo.IdClass> {

}
