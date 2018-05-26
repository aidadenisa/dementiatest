package dt.service;

import dt.model.ConnectPoint;
import dt.repository.ConnectPointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectPointsService {

    @Autowired
    private ConnectPointsRepository connectPointsRepository;

    public void savePoints(List<ConnectPoint> connectPoints) {
        connectPointsRepository.save(connectPoints);
    }
}
