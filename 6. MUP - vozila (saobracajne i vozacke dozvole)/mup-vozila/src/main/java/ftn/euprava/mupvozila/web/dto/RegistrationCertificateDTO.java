package ftn.euprava.mupvozila.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrationCertificateDTO {

    private Long id;

    private String licensePlate;

    private LocalDate dayOfIssue;

    private String placeOfIssue;

    private Boolean request;

    private Boolean status;

    private String userId;

    private CarDTO carDTO;
}
