package ftn.euprava.zdravstvo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BirthCertificateRequest {

    private String identificationNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private String countryOfBirth;
    private String citizenship;
    private String parent1Id;
    private String parent2Id;

}
