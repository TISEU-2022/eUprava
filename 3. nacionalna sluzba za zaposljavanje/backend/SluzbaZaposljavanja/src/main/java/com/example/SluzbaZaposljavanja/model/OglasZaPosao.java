package com.example.SluzbaZaposljavanja.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="oglasi_za_posao")
public class OglasZaPosao {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "oglas_posla_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "naziv", unique = false, nullable = false)
    private String naziv;

    @Column(name = "opis", unique = false, nullable = false)
    private String opis;

    @Column(name = "datum_od", unique = false, nullable = false)
    private LocalDate datumOd;

    @Column(name = "datum_do", unique = false, nullable = false)
    private LocalDate datumDo;

    @ManyToOne
    @JoinColumn(name = "firma_id", referencedColumnName = "firma_id")
    private Firma firma;

    @ManyToOne
    @JoinColumn(name = "vrsta_posla_id", referencedColumnName = "vrsta_posla_id")
    private VrstaPosla vrstaPosla;

    @OneToMany(cascade = {ALL}, mappedBy = "oglasZaPosao")
    private List<Konkurs> konkursi = new ArrayList<Konkurs>();

    public OglasZaPosao() {
    }

    public OglasZaPosao(Integer id, String naziv, String opis, LocalDate datumOd, LocalDate datumDo, Firma firma, VrstaPosla vrstaPosla) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.firma = firma;
        this.vrstaPosla = vrstaPosla;
    }

    public Integer getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public LocalDate getDatumOd() {
        return datumOd;
    }

    public LocalDate getDatumDo() {
        return datumDo;
    }

    public Firma getFirma() {
        return firma;
    }

    public VrstaPosla getVrstaPosla() {
        return vrstaPosla;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setDatumOd(LocalDate datumOd) {
        this.datumOd = datumOd;
    }

    public void setDatumDo(LocalDate datumDo) {
        this.datumDo = datumDo;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public void setVrstaPosla(VrstaPosla vrstaPosla) {
        this.vrstaPosla = vrstaPosla;
    }

    public List<Konkurs> getKonkursi() {
        return konkursi;
    }

    public void setKonkursi(List<Konkurs> konkursi) {
        this.konkursi = konkursi;
    }

    @Override
    public String toString() {
        return "OglasZaPosao{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", datumOd=" + datumOd +
                ", datumDo=" + datumDo +
                ", firma=" + firma +
                ", vrstaPosla=" + vrstaPosla +
                '}';
    }
}
