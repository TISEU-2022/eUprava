package ftn.euprava.mupvozila.repository;

import ftn.euprava.mupvozila.model.RequestForDrivingLicence;
import ftn.euprava.mupvozila.model.enums.RequestStatus;
import ftn.euprava.mupvozila.web.dto.RequestForDrivingLicenceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestForDrivingLicenceRepository extends JpaRepository<RequestForDrivingLicence, Long> {

    // fetching request for ADMIN and EMPLOYEE (only if request status is PENDING)
    Page<RequestForDrivingLicence> findAllByRequestStatus(RequestStatus requestStatus, Pageable pageable);

    // fetching request for EMPLOYEE
    Page<RequestForDrivingLicence> findAllByEmployeeIdAndRequestStatus(String employeeId, RequestStatus requestStatus, Pageable pageable);

    // fetching request for CITIZEN
    Page<RequestForDrivingLicence> findAllByCitizenIdEqualsAndRequestStatus(String citizenId, RequestStatus requestStatus, Pageable pageable);
    //Page<RequestForDrivingLicence> findAllByCitizenId(String citizenId, Pageable pageable);

    // used for checking if PENDING request exist for citizen
    List<RequestForDrivingLicence> findAllByCitizenIdEqualsAndRequestStatus(String citizenId, RequestStatus requestStatus);

}
