package com.example.SluzbaZaposljavanja.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "gradjani")
public class Gradjanin {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "gradjanin_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "ime", unique = false, nullable = false)
    private String ime;

    @Column(name = "prezime", unique = false, nullable = false)
    private String prezime;

    @Column(name = "email", unique = false, nullable = false)
    private String email;

    @Column(name = "korisnicko_ime", unique = true, nullable = false)
    private String korisnickoIme;

    @Column(name = "lozinka", unique = false, nullable = false)
    private String lozinka;

    @Column(name = "datum_rodjenja", unique = false, nullable = false)
    private LocalDate datumRodjenja;

    @Column(name = "jmbg", unique = false, nullable = false)
    private String jmbg;

    @ManyToOne
    @JoinColumn(name = "firma_id", referencedColumnName = "firma_id")
    private Firma firma;

    @JsonIgnore
    @OneToMany(cascade = {ALL}, mappedBy = "gradjanin")
    private List<ZahtevEvidencije> zahteviEvidencije = new ArrayList<ZahtevEvidencije>();

    @JsonIgnore
    @OneToMany(cascade = {ALL}, mappedBy = "gradjanin")
    private List<Konkurs> oglasiZaPosao = new ArrayList<Konkurs>();

    public Gradjanin() {
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

    public String getEmail() {
        return email;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getJmbg() {
        return jmbg;
    }

    public Firma getFirma() {
        return firma;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }




    public Gradjanin(String ime, String prezime, String email, String korisnickoIme, String lozinka, LocalDate datumRodjenja, String jmbg, Firma firma) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.datumRodjenja = datumRodjenja;
        this.jmbg = jmbg;
        this.firma = firma;
    }

    public Gradjanin(Integer id, String ime, String prezime, String email, String korisnickoIme, String lozinka, LocalDate datumRodjenja, String jmbg, Firma firma) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.datumRodjenja = datumRodjenja;
        this.jmbg = jmbg;
        this.firma = firma;
    }

    @Override
    public String toString() {
        return "Gradjanin{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", email='" + email + '\'' +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", datumRodjenja=" + datumRodjenja +
                ", jmbg=" + jmbg +
                ", firma=" + firma +
                '}';
    }
}
