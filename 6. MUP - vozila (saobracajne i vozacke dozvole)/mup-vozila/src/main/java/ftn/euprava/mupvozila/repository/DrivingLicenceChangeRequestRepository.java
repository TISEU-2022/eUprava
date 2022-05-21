package ftn.euprava.mupvozila.repository;

import ftn.euprava.mupvozila.model.DrivingLicenceChangeRequest;
import ftn.euprava.mupvozila.model.RequestForDrivingLicence;
import ftn.euprava.mupvozila.model.enums.RequestStatus;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceChangeRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrivingLicenceChangeRequestRepository extends JpaRepository<DrivingLicenceChangeRequest, Long> {

    // fetching request for ADMIN and EMPLOYEE (only if request status is PENDING)
    Page<DrivingLicenceChangeRequest> findAllByRequestStatus(RequestStatus requestStatus, Pageable pageable);

    // fetching request for EMPLOYEE
    Page<DrivingLicenceChangeRequest> findAllByEmployeeIdAndRequestStatus(String employeeId, RequestStatus requestStatus, Pageable pageable);

    // fetching request for CITIZEN
    Page<DrivingLicenceChangeRequest> findAllByDrivingLicenceUserIdAndRequestStatus(String citizenId, RequestStatus requestStatus, Pageable pageable);
    // used for checking if PENDING request exist for citizen
    List<DrivingLicenceChangeRequest> findAllByDrivingLicenceUserIdAndRequestStatus(String citizenId, RequestStatus requestStatus);

}
