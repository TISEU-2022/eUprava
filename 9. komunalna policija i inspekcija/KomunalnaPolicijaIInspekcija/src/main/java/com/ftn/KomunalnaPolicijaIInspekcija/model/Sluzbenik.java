package com.ftn.KomunalnaPolicijaIInspekcija.model;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sluzbenik {

    private UUID id;
    private String ime;
    private String prezime;
    private String email;
    private String jmbg;


}
