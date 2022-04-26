package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Sluzbenik;

import java.util.Date;

public class IzvestajDTO {

    private Long id;
    private String izvestaj;
    private Boolean prihvaceno;
    private Date vremePodnosenja;
    private Sluzbenik sluzbenik;

}
