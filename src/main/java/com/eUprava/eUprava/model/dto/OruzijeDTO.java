package com.eUprava.eUprava.model.dto;


import com.eUprava.eUprava.model.entity.OruzniList;
import com.eUprava.eUprava.model.entity.ZahtevZaNabavku;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OruzijeDTO implements Serializable {


    private Long oruzije_id;
    private String markaOruzija;
    private String vrstaoruzija;
    private String kalibar;
    private Long serijskiBroj;
    private OruzniList list;
    private ZahtevZaNabavku zahtev;
}
