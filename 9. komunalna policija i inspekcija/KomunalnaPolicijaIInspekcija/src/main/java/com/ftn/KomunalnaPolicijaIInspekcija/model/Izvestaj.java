package com.ftn.KomunalnaPolicijaIInspekcija.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Izvestaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "izvestaj", nullable = true)
    private String izvestaj;

    @Column(name = "prihvaceno", nullable = true)
    private Boolean prihvaceno;

    @Column(name = "vremePodnosenja", nullable = true)
    private Date vremePodnosenja;

    @ManyToOne
    private Sluzbenik sluzbenik;

}
