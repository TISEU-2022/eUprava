package com.ftn.KomunalnaPolicijaIInspekcija.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Podnosilac {

    private UUID id;
    private String ime;
    private String prezime;
    private String jmbg;
    private String adresa;
    private String mesto;
    private String email;
    private String telefon;
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
                ", telefon='" + telefon + '\'' +
                ", pttBroj=" + pttBroj +
                '}';
    }
}
