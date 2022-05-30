package ftn.euprava.zdravstvo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BirthCertificateRequest {

    @NotNull
    @Size(min=13, max=13)
    private String identificationNumber;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String gender;
    @NotNull
    private Date dateOfBirth;
    @NotNull
    private String countryOfBirth;
    @NotNull
    private String citizenship;

    private String parent1Id;

    private String parent2Id;

    @Override
    public String toString() {
        return "BirthCertificateRequest{" +
                "identificationNumber='" + identificationNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", countryOfBirth='" + countryOfBirth + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", parent1Id='" + parent1Id + '\'' +
                ", parent2Id='" + parent2Id + '\'' +
                '}';
    }
}
