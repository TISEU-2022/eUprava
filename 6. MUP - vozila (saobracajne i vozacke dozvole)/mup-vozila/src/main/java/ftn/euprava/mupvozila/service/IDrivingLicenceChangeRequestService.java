package ftn.euprava.mupvozila.service;

import ftn.euprava.mupvozila.model.DrivingLicenceChangeRequest;
import ftn.euprava.mupvozila.model.enums.RequestStatus;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceChangeRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDrivingLicenceChangeRequestService {

    DrivingLicenceChangeRequest findOne(Long id);

    DrivingLicenceChangeRequestDTO findPendingRequest(String userId);

    Page<DrivingLicenceChangeRequestDTO> findAll(String token, RequestStatus requestStatus, Pageable pageable);

    DrivingLicenceChangeRequestDTO save(DrivingLicenceChangeRequestDTO drivingLicenceChangeRequestDTO);

    void delete(Long id);
}
