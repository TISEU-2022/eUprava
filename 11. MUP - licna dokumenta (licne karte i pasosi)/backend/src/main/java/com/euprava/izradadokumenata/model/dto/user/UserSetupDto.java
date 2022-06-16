package com.euprava.izradadokumenata.model.dto.user;

import com.euprava.izradadokumenata.model.Municipality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSetupDto {

    private String username;

    private String jmbg;

    private String name;

    private String lastName;

    private LocalDate dateOfBirth;

    private String countryOfBirth;

    private String citizenship;

    private String gender;

    private Municipality municipality;
}
