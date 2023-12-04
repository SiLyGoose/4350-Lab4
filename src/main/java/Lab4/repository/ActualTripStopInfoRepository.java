package Lab4.repository;

import Lab4.entity.ActualTripStopInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActualTripStopInfoRepository extends JpaRepository<ActualTripStopInfo, ActualTripStopInfo.IdClass> {

}
