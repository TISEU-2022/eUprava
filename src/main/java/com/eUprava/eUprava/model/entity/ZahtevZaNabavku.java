package com.eUprava.eUprava.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "zahteviZaNabavku")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ZahtevZaNabavku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nabavka_id", unique = true, nullable = false)
    private Long nabavka_id;

    @Column(name = "vazecaLicna", unique = false, nullable = false)
    private Boolean vazecaLicna;

    @Column(name = "sudskoUverenje", unique = false, nullable = false)
    private Boolean sudskoUverenje;

    @Column(name = "kolicina", unique = false, nullable = false)
    private String kolicina;

    @Column(name = "lekarskoUverenje", unique = false, nullable = false)
    private Boolean lekarskoUverenje;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oruzije_id", referencedColumnName = "id")
    private Oruzije oruzije;
}
