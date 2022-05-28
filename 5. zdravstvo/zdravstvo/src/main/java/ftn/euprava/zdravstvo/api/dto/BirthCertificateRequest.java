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
    @NotEmpty
    @Size(min=13, max=13)
    private String identificationNumber;
    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
    @NotNull
    @NotEmpty
    private String gender;
    @NotNull
    @NotEmpty
    private Date dateOfBirth;
    @NotNull
    @NotEmpty
    private String countryOfBirth;
    @NotNull
    @NotEmpty
    private String citizenship;

    private String parent1Id;

    private String parent2Id;



}
