package com.eUprava.eUprava.payload;

import com.eUprava.eUprava.model.entity.OruzniList;
import com.eUprava.eUprava.model.entity.ZahtevZaNabavku;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class OruzijePostRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    private String markaOruzija;

    @NotBlank
    @Size(min = 3,max = 300)
    private String vrstaOruzija;

    @NotBlank
    @Size(min = 3,max = 300)
    private String kalibar;

    @NotBlank
    private Long serijskiBroj;

    @NotBlank
    @Size(min = 3, max = 50)
    private OruzniList list;

    @NotBlank
    @Size(min = 3, max = 50)
    private ZahtevZaNabavku zahtev;
}
