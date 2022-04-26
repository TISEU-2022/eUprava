package com.ftn.KomunalnaPolicijaIInspekcija.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Izvestaj {

    private Long id;
    private String izvestaj;
    private Boolean prihvaceno;
    private Date vremePodnosenja;
    private Sluzbenik sluzbenik;
    private KomunalniProblem komunalniProblem;

}
