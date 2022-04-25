package ftn.euprava.mupvozila.service;

import ftn.euprava.mupvozila.model.DrivingLicence;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceDTO;

import java.util.List;

public interface IDrivingLicenceService {

    DrivingLicenceDTO findOne(Long id);

    DrivingLicenceDTO findOneByIdentityNumber(String identityNumber);

    List<DrivingLicence> findAll();

    DrivingLicence save(DrivingLicence drivingLicence);

    void delete(Long id);
}
