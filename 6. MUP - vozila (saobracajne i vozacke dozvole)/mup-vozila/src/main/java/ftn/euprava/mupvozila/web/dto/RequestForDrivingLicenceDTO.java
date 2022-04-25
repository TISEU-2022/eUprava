package ftn.euprava.mupvozila.web.dto;

import ftn.euprava.mupvozila.model.DrivingLicenceType;
import lombok.Data;

@Data
public class RequestForDrivingLicenceDTO {

    private Long id;

    private DrivingLicenceType drivingLicenceType;

    private UserDTO citizen;

    private UserDTO employee;
}
