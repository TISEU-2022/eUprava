package com.euprava.izradadokumenata.model.dto.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SimpleUserDto {

    private String username;

    private String name;

    private String jmbg;

    private String lastName;

    private LocalDate dateOfBirth;

}
