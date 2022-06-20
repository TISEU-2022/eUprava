package com.example.SluzbaZaposljavanja.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vrste_posla")
public class VrstaPosla implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "vrsta_posla_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "ime", unique = false, nullable = false)
    private String ime;

    public VrstaPosla() {
    }

    public VrstaPosla(String ime) {
        this.ime = ime;
    }

    public Integer getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public String toString() {
        return "VrstaPosla{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                '}';
    }
}
