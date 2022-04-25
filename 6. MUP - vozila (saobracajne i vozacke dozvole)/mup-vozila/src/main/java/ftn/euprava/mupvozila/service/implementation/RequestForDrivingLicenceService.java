package ftn.euprava.mupvozila.service.implementation;

import ftn.euprava.mupvozila.model.RequestForDrivingLicence;
import ftn.euprava.mupvozila.repository.RequestForDrivingLicenceRepository;
import ftn.euprava.mupvozila.service.IRequestForDrivingLicenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestForDrivingLicenceService implements IRequestForDrivingLicenceService {

    private final RequestForDrivingLicenceRepository drivingLicenceRepository;

    public RequestForDrivingLicenceService(RequestForDrivingLicenceRepository drivingLicenceRepository) {
        this.drivingLicenceRepository = drivingLicenceRepository;
    }

    @Override
    public RequestForDrivingLicence findOne(Long id) {
        return drivingLicenceRepository.findById(id).orElse(null);
    }

    @Override
    public List<RequestForDrivingLicence> findAll() {
        return drivingLicenceRepository.findAll();
    }

    @Override
    public RequestForDrivingLicence save(RequestForDrivingLicence drivingLicence) {
        return drivingLicenceRepository.save(drivingLicence);
    }

    @Override
    public void delete(Long id) {
        drivingLicenceRepository.deleteById(id);
    }
}
