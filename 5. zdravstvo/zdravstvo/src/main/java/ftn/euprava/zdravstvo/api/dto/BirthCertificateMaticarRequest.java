package ftn.euprava.zdravstvo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BirthCertificateMaticarRequest {
    private String identification_number;
    private String first_name;
    private String last_name;
    private String gender;
    private String date_of_birth;
    private String country_of_birth;
    private String citizenship;

    public static BirthCertificateMaticarRequest of(BirthCertificateRequest request) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return new BirthCertificateMaticarRequest(
                request.getIdentificationNumber(),
                request.getFirstName(),
                request.getLastName(),
                request.getGender(),
                formatter.format(request.getDateOfBirth()),
                request.getCountryOfBirth(),
                request.getCitizenship()
                );
    }
}
