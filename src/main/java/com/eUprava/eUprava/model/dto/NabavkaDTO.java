package com.eUprava.eUprava.model.dto;

import com.eUprava.eUprava.model.entity.Oruzije;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NabavkaDTO implements Serializable {

    private Long nabavka_id;
    private Boolean vazecaLicna;
    private Boolean sudskoUverenje;
    private String kolicina;
    private Boolean lekarskoUverenje;
    private Oruzije oruzije;
}
