package ftn.euprava.mupvozila.web.dto;

import ftn.euprava.mupvozila.model.enums.DrivingLicenceType;
import ftn.euprava.mupvozila.model.enums.RequestStatus;
import ftn.euprava.mupvozila.model.enums.RequestType;
import lombok.Data;

@Data
public class DrivingLicenceChangeRequestDTO {

    private Long id;

    private RequestType requestType;

    private DrivingLicenceDTO drivingLicenceDTO;

    private String employeeId;

    private RequestStatus requestStatus;
}
