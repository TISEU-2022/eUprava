package com.eUprava.eUprava.model.dto;
import com.eUprava.eUprava.model.entity.Oruzije;

import com.eUprava.eUprava.model.entity.ZahtevZaNosenje;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OruzniListDTO implements Serializable {


    private Long list_id;
    private String imeVlasnika;
    private String prezimeVlasnika;
    private Date datumIzdavanja;
    private Date vaziDo;
    private Long registarskiBroj;
    private Long serijskiBrojOruzija;
    private Oruzije oruzije;
    private ZahtevZaNosenje zahtev;
}
