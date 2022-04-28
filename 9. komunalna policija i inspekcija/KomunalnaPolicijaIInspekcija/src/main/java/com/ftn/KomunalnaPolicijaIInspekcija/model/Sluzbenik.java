package com.ftn.KomunalnaPolicijaIInspekcija.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table
public class Sluzbenik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ime", nullable = true)
    private String ime;

    @Column(name = "prezime", nullable = true)
    private String prezime;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "jmbg", nullable = false)
    private String jmbg;


    public Sluzbenik(String ime, String prezime, String email, String jmbg) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.jmbg = jmbg;
    }
}
