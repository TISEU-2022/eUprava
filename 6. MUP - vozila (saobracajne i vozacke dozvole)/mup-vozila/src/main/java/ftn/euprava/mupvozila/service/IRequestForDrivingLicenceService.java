package ftn.euprava.mupvozila.service;

import ftn.euprava.mupvozila.model.RequestForDrivingLicence;

import java.util.List;

public interface IRequestForDrivingLicenceService {

    RequestForDrivingLicence findOne(Long id);

    List<RequestForDrivingLicence> findAll();

    RequestForDrivingLicence save(RequestForDrivingLicence drivingLicence);

    void delete(Long id);
}
