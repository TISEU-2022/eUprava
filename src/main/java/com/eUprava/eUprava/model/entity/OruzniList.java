package com.eUprava.eUprava.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orzniList")
public class OruzniList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id", unique = true, nullable = false)
    private Long list_id;

    @Column(name = "ime", unique = false, nullable = false)
    private String imeVlasnika;

    @Column(name = "prezime", unique = false, nullable = false)
    private String prezimeVlasnika;

    @Column(name = "datumIzdavanja", unique = false, nullable = false)
    private Date datumIzdavanja;

    @Column(name = "vaziDo", unique = false, nullable = false)
    private Date vaziDo;

    @Column(name = "registarskiBroj", unique = true, nullable = false)
    private Long registarskiBroj;

    @Column(name = "serijskiBrojOruzija", unique = true, nullable = false)
    private Long serijskiBrojOruzija;

    @OneToOne(mappedBy = "list")
    private Oruzije oruzije;

    @OneToOne(mappedBy = "list")
    private ZahtevZaNosenje zahtev;


}
