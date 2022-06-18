package yu.rs.co.edfeahs.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BirthCertificateDto {

    @JsonProperty("identification_number")
    private String identificationNumber;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("country_of_birth")
    private String countryOfBirth;

    @JsonProperty("citizenship")
    private String citizenship;

    


}
