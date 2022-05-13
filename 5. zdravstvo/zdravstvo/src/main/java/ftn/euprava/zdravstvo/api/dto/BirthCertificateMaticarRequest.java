package ftn.euprava.zdravstvo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BirthCertificateMaticarRequest {
    private String identification_number;
    private String first_name;
    private String last_name;
    private String gender;
    private Date date_of_birth;
    private String country_of_birth;
    private String citizenship;

    public static BirthCertificateMaticarRequest of(BirthCertificateRequest request) {
        return new BirthCertificateMaticarRequest(
                request.getIdentificationNumber(),
                request.getFirstName(),
                request.getLastName(),
                request.getGender(),
                request.getDateOfBirth(),
                request.getCountryOfBirth(),
                request.getCitizenship()
                );
    }
}
