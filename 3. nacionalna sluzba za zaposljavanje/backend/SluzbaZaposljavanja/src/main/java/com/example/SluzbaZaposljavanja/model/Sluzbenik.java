package com.example.SluzbaZaposljavanja.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="sluzbenici")
public class Sluzbenik {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "sluzbenik_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "ime", unique = false, nullable = false)
    private String ime;

    @Column(name = "prezime", unique = false, nullable = false)
    private String prezime;

    @Column(name = "korisnicko_ime", unique = true, nullable = false)
    private String korisnickoIme;

    @Column(name = "email", unique = false, nullable = false)
    private String email;

    @Column(name = "jmbg", unique = false, nullable = false)
    private LocalDate jmbg;

    public Sluzbenik() {
    }

    public Sluzbenik(Integer id, String ime, String prezime, String korisnickoIme, String email, LocalDate jmbg) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.jmbg = jmbg;
    }

    public Sluzbenik(String ime, String prezime, String korisnickoIme, String email, LocalDate jmbg) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.jmbg = jmbg;
    }

    public Integer getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getJmbg() {
        return jmbg;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJmbg(LocalDate jmbg) {
        this.jmbg = jmbg;
    }


}
