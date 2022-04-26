package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Sluzbenik;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Builder
@Data
@ToString
public class IzvestajDTO {

    private Long id;
    private String izvestaj;
    private Boolean prihvaceno;
    private Date vremePodnosenja;
    private SluzbenikDTO sluzbenikDTO;

}
