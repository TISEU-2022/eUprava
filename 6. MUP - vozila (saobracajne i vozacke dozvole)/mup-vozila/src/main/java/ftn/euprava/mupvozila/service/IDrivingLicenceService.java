package ftn.euprava.mupvozila.service;

import ftn.euprava.mupvozila.model.DrivingLicence;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceChangeRequestDTO;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceDTO;
import ftn.euprava.mupvozila.web.dto.RequestForDrivingLicenceDTO;

import java.util.List;

public interface IDrivingLicenceService {

    DrivingLicenceDTO findOne(Long id);

    DrivingLicenceDTO findOneByIdentityNumber(String identityNumber);

    DrivingLicenceDTO findOneByUserId(String userId);

    List<DrivingLicence> findAll();

    DrivingLicenceDTO save(RequestForDrivingLicenceDTO requestForDrivingLicenceDTO);

    DrivingLicenceDTO update(DrivingLicenceChangeRequestDTO drivingLicenceChangeRequestDTO);

    void delete(Long id);
}
