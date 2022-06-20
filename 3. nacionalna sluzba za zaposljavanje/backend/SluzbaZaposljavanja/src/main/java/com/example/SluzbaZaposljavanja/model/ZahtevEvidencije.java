package com.example.SluzbaZaposljavanja.model;

import javax.persistence.*;

@Entity
@Table(name="zahtevi_evidencije")
public class ZahtevEvidencije {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "zahtev_evidencije_id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "gradjanin_id", referencedColumnName = "gradjanin_id")
    private Gradjanin gradjanin;

    @Column(name = "adresa", unique = false, nullable = false)
    private String adresa;

    @Column(name = "drzava_rodjenja", unique = false, nullable = false)
    private String drzavaRodjenja;

    @Column(name = "ulica", unique = false, nullable = false)
    private String ulica;

    @Column(name = "telefon", unique = false, nullable = false)
    private String telefon;

    @Column(name = "opstina", unique = false, nullable = false)
    private String opstina;

    public ZahtevEvidencije() {
    }

    public ZahtevEvidencije(Gradjanin gradjanin, String adresa, String drzavaRodjenja, String ulica, String telefon, String opstina) {
        this.gradjanin = gradjanin;
        this.adresa = adresa;
        this.drzavaRodjenja = drzavaRodjenja;
        this.ulica = ulica;
        this.telefon = telefon;
        this.opstina = opstina;
    }

    public Integer getId() {
        return id;
    }

    public Gradjanin getGradjanin() {
        return gradjanin;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getDrzavaRodjenja() {
        return drzavaRodjenja;
    }

    public String getUlica() {
        return ulica;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getOpstina() {
        return opstina;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGradjanin(Gradjanin gradjanin) {
        this.gradjanin = gradjanin;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setDrzavaRodjenja(String drzavaRodjenja) {
        this.drzavaRodjenja = drzavaRodjenja;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }

    @Override
    public String toString() {
        return "ZahtevEvidencije{" +
                "id=" + id +
                ", gradjanin=" + gradjanin +
                ", adresa='" + adresa + '\'' +
                ", drzavaRodjenja='" + drzavaRodjenja + '\'' +
                ", ulica='" + ulica + '\'' +
                ", telefon='" + telefon + '\'' +
                ", opstina='" + opstina + '\'' +
                '}';
    }
}
