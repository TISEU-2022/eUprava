package com.ftn.KomunalnaPolicijaIInspekcija.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class Podnosilac {

    @Id
    @Column(name = "id", unique = true, nullable = false)
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
