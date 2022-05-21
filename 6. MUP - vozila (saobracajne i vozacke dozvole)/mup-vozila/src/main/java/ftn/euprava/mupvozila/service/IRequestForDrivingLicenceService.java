package ftn.euprava.mupvozila.service;

import ftn.euprava.mupvozila.model.RequestForDrivingLicence;
import ftn.euprava.mupvozila.model.enums.RequestStatus;
import ftn.euprava.mupvozila.web.dto.RequestForDrivingLicenceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRequestForDrivingLicenceService {

    RequestForDrivingLicence findOne(Long id);

    RequestForDrivingLicenceDTO findPendingRequest(String userId);

    Page<RequestForDrivingLicenceDTO> findAll(String token, RequestStatus requestStatus, Pageable pageable);

    RequestForDrivingLicenceDTO save(RequestForDrivingLicenceDTO requestForDrivingLicenceDTO);

    void delete(Long id);
}
