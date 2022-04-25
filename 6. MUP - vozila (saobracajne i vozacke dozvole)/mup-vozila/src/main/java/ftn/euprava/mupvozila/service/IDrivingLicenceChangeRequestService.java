package ftn.euprava.mupvozila.service;

import ftn.euprava.mupvozila.model.DrivingLicenceChangeRequest;

import java.util.List;

public interface IDrivingLicenceChangeRequestService {

    DrivingLicenceChangeRequest findOne(Long id);

    List<DrivingLicenceChangeRequest> findAll();

    DrivingLicenceChangeRequest save(DrivingLicenceChangeRequest drivingLicenceChangeRequest);

    void delete(Long id);
}
