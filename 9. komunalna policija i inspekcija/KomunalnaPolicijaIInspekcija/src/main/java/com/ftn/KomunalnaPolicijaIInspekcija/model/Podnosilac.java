package com.ftn.KomunalnaPolicijaIInspekcija.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class Podnosilac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String jmbg;

    private String ime;
    private String prezime;
    private String adresa;
    private String mesto;
    private String email;
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
