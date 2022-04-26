package com.ftn.KomunalnaPolicijaIInspekcija.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VrstaKomunalnogProblema {

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
