package Lab4.service;

import Lab4.repository.ActualTripStopInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActualTripStopInfoService {
    @Autowired
    ActualTripStopInfoRepository repo;


}
