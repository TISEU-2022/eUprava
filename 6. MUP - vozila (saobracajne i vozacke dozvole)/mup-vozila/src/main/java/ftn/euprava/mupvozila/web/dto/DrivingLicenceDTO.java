package ftn.euprava.mupvozila.web.dto;

import ftn.euprava.mupvozila.model.enums.DrivingLicenceType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DrivingLicenceDTO {

    private Long id;

    private String licenceNumber;

    private LocalDate dayOfIssue;

    private LocalDate validUntil;

    private String placeOfIssue;

    private DrivingLicenceType drivingLicenceType;

    private String userId;

}
