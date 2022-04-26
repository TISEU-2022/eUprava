package com.example.SluzbaZaposljavanja.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="konkursi")
public class Konkurs {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "konkurs_id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "gradjanin_id", referencedColumnName = "gradjanin_id")
    private Gradjanin gradjanin;

    @ManyToOne
    @JoinColumn(name = "oglas_posla_id", referencedColumnName = "oglas_posla_id")
    private OglasZaPosao oglasZaPosao;

    @Column(name = "datumKonkurisanja", unique = false, nullable = false)
    private LocalDate datumKonkurisanja;

    @Column(name = "zavrseno", unique = false, nullable = false)
    private Boolean zavrseno;

    public Konkurs() {
    }

    public Konkurs(Integer id, Gradjanin gradjanin, OglasZaPosao oglasZaPosao, LocalDate datumKonkurisanja, Boolean zavrseno) {
        this.id = id;
        this.gradjanin = gradjanin;
        this.oglasZaPosao = oglasZaPosao;
        this.datumKonkurisanja = datumKonkurisanja;
        this.zavrseno = zavrseno;
    }

    public Integer getId() {
        return id;
    }

    public Gradjanin getGradjanin() {
        return gradjanin;
    }

    public OglasZaPosao getOglasZaPosao() {
        return oglasZaPosao;
    }

    public LocalDate getDatumKonkurisanja() {
        return datumKonkurisanja;
    }

    public Boolean getZavrseno() {
        return zavrseno;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGradjanin(Gradjanin gradjanin) {
        this.gradjanin = gradjanin;
    }

    public void setOglasZaPosao(OglasZaPosao oglasZaPosao) {
        this.oglasZaPosao = oglasZaPosao;
    }

    public void setDatumKonkurisanja(LocalDate datumKonkurisanja) {
        this.datumKonkurisanja = datumKonkurisanja;
    }

    public void setZavrseno(Boolean zavrseno) {
        this.zavrseno = zavrseno;
    }

    @Override
    public String toString() {
        return "Konkurs{" +
                "id=" + id +
                ", gradjanin=" + gradjanin +
                ", oglasZaPosao=" + oglasZaPosao +
                ", datumKonkurisanja=" + datumKonkurisanja +
                ", zavrseno=" + zavrseno +
                '}';
    }
}
