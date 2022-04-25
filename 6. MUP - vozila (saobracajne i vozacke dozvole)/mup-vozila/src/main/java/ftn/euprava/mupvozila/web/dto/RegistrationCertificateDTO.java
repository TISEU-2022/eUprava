package ftn.euprava.mupvozila.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrationCertificateDTO {

    private Long id;

    private String licencePlate;

    private LocalDate dayOfIssue;

    private String placeOfIssue;

    private UserDTO userDTO;

    private CarDTO carDTO;
}
