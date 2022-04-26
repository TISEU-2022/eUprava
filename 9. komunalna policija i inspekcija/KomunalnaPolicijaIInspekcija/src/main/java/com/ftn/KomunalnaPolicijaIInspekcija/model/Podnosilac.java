package com.ftn.KomunalnaPolicijaIInspekcija.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Podnosilac {

    @Id
    private UUID id;
    private String ime;
    private String prezime;

    @Column(name = "jmbg", unique = true)
    private String jmbg;
    private String adresa;
    private String mesto;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "brojTelefona", unique = true)
    private String brojTelefona;
    private int pttBroj;

    @Override
    public String toString() {
        return "Podnosilac{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", jmbg='" + jmbg + '\'' +
                ", adresa='" + adresa + '\'' +
                ", mesto='" + mesto + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + brojTelefona + '\'' +
                ", pttBroj=" + pttBroj +
                '}';
    }
}
