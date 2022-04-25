package ftn.euprava.mupvozila.service.implementation;

import ftn.euprava.mupvozila.model.DrivingLicenceChangeRequest;
import ftn.euprava.mupvozila.repository.DrivingLicenceChangeRequestRepository;
import ftn.euprava.mupvozila.service.IDrivingLicenceChangeRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrivingLicenceChangeRequestService implements IDrivingLicenceChangeRequestService {

    private final DrivingLicenceChangeRequestRepository drivingLicenceChangeRequestRepository;

    public DrivingLicenceChangeRequestService(DrivingLicenceChangeRequestRepository drivingLicenceChangeRequestRepository) {
        this.drivingLicenceChangeRequestRepository = drivingLicenceChangeRequestRepository;
    }

    @Override
    public DrivingLicenceChangeRequest findOne(Long id) {
        return drivingLicenceChangeRequestRepository.findById(id).orElse(null);
    }

    @Override
    public List<DrivingLicenceChangeRequest> findAll() {
        return drivingLicenceChangeRequestRepository.findAll();
    }

    @Override
    public DrivingLicenceChangeRequest save(DrivingLicenceChangeRequest drivingLicenceChangeRequest) {
        return drivingLicenceChangeRequestRepository.save(drivingLicenceChangeRequest);
    }

    @Override
    public void delete(Long id) {
        drivingLicenceChangeRequestRepository.deleteById(id);
    }
}
