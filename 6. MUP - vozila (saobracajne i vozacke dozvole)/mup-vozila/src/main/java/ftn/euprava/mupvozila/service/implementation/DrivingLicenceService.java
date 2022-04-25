package ftn.euprava.mupvozila.service.implementation;

import ftn.euprava.mupvozila.model.DrivingLicence;
import ftn.euprava.mupvozila.repository.DrivingLicenceRepository;
import ftn.euprava.mupvozila.service.IDrivingLicenceService;
import ftn.euprava.mupvozila.util.mapper.DrivingLicenceMapper;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrivingLicenceService implements IDrivingLicenceService {

    private final DrivingLicenceRepository drivingLicenceRepository;
    private final DrivingLicenceMapper drivingLicenceMapper;

    public DrivingLicenceService(DrivingLicenceRepository drivingLicenceRepository, DrivingLicenceMapper drivingLicenceMapper) {
        this.drivingLicenceRepository = drivingLicenceRepository;
        this.drivingLicenceMapper = drivingLicenceMapper;
    }

    @Override
    public DrivingLicenceDTO findOne(Long id) {
        DrivingLicence drivingLicence = drivingLicenceRepository.findById(id).orElse(null);

        //TODO custom exception
        if(drivingLicence == null)
            throw new RuntimeException();

        return drivingLicenceMapper.toDto(drivingLicence);
    }

    @Override
    public DrivingLicenceDTO findOneByIdentityNumber(String identityNumber) {

        DrivingLicence drivingLicence = drivingLicenceRepository.findByUser_IdentityNumber(identityNumber).orElse(null);

        //TODO custom exception
        if(drivingLicence == null)
            throw new RuntimeException();

        return drivingLicenceMapper.toDto(drivingLicence);
    }

    @Override
    public List<DrivingLicence> findAll() {
        return drivingLicenceRepository.findAll();
    }

    @Override
    public DrivingLicence save(DrivingLicence drivingLicence) {
        return drivingLicenceRepository.save(drivingLicence);
    }

    @Override
    public void delete(Long id) {
        drivingLicenceRepository.deleteById(id);
    }
}
