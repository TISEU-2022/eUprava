package com.eUprava.eUprava.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KorisnikDTO {
    private Long id;

    private String ime;

    private String prezime;

    private String username;

    private String role;
}
