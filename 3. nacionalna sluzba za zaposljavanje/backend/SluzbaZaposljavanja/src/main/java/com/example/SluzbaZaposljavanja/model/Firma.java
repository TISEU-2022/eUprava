package com.example.SluzbaZaposljavanja.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "firme")
public class Firma implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "firma_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "ime_firme", unique = false, nullable = false)
    private String imeFirme;

    @Column(name = "oblast_poslovanja", unique = false, nullable = false)
    private String oblastPoslovanja;

    @OneToMany(cascade = {ALL}, mappedBy = "firma")
    private List<Gradjanin> zaposleni = new ArrayList<Gradjanin>();

    @OneToMany(cascade = {ALL}, mappedBy = "firma")
    private List<OglasZaPosao> oglasiZaPosao = new ArrayList<OglasZaPosao>();

    public Firma() {
    }

    public Firma(String imeFirme, String oblastPoslovanja) {
        this.imeFirme = imeFirme;
        this.oblastPoslovanja = oblastPoslovanja;
    }

    public Integer getId() {
        return id;
    }

    public String getImeFirme() {
        return imeFirme;
    }

    public String getOblastPoslovanja() {
        return oblastPoslovanja;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImeFirme(String imeFirme) {
        this.imeFirme = imeFirme;
    }

    public void setOblastPoslovanja(String oblastPoslovanja) {
        this.oblastPoslovanja = oblastPoslovanja;
    }

    public List<Gradjanin> getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(List<Gradjanin> zaposleni) {
        this.zaposleni = zaposleni;
    }
}
