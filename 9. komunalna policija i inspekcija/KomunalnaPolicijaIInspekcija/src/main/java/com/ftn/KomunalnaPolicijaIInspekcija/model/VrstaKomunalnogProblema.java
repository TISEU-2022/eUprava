package com.ftn.KomunalnaPolicijaIInspekcija.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vrstaKomunalnogProblema")
public class VrstaKomunalnogProblema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;

    @Override
    public String toString() {
        return "VrstaKomunalnogProblema{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}
