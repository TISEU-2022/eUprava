package com.eUprava.eUprava.payload;

import com.eUprava.eUprava.model.entity.Oruzije;
import com.eUprava.eUprava.model.entity.ZahtevZaNosenje;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
public class OruzniListPostRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    private String imeVlasnika;

    @NotBlank
    @Size(min = 3, max = 50)
    private String prezimeVlasnika;

    @NotBlank
    @Size(min = 3, max = 50)
    private Date datumIzdavanja;

    @NotBlank
    @Size(min = 3, max = 50)
    private Date vaziDo;

    @NotBlank
    @Size(min = 3, max = 50)
    private Long registarskiBroj;

    @NotBlank
    @Size(min = 3, max = 50)
    private Long serijskiBrojOruzija;
    @NotBlank
    @Size(min = 3, max = 50)
    private Oruzije oruzije;
    @NotBlank
    @Size(min = 3, max = 50)
    private ZahtevZaNosenje zahtev;
}
