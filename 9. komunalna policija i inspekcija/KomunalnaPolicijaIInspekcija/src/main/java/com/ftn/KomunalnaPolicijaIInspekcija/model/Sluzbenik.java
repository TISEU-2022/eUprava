package com.ftn.KomunalnaPolicijaIInspekcija.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Sluzbenik {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "ime", nullable = true)
    private String ime;

    @Column(name = "prezime", nullable = true)
    private String prezime;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "jmbg", nullable = false)
    private String jmbg;


}
