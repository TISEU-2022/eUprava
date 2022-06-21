package ftn.euprava.mupvozila.web.dto;

import ftn.euprava.mupvozila.model.enums.DrivingLicenceType;
import ftn.euprava.mupvozila.model.enums.RequestStatus;
import lombok.Data;

@Data
public class RequestForDrivingLicenceDTO {

    private Long id;

    private DrivingLicenceType drivingLicenceType;

    private String citizenId;

    private String employeeId;

    private DrivingLicenceDTO drivingLicenceDTO;

    private RequestStatus requestStatus;
}
