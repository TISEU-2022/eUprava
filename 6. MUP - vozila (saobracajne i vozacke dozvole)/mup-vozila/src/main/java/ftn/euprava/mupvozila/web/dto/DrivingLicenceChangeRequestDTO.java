package ftn.euprava.mupvozila.web.dto;

import ftn.euprava.mupvozila.model.enums.DrivingLicenceType;
import ftn.euprava.mupvozila.model.enums.RequestType;
import lombok.Data;

@Data
public class DrivingLicenceChangeRequestDTO {

    private Long id;

    private RequestType requestType;

    private DrivingLicenceType drivingLicenceType;

    private DrivingLicenceDTO drivingLicenceDTO;
}
