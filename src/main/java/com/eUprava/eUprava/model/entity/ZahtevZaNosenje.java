package com.eUprava.eUprava.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "zahteviZaNosenje")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ZahtevZaNosenje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nosenje_id", unique = true, nullable = false)
    private Long nosenje_id;

    @Column(name = "vazecaLicna", unique = false, nullable = false)
    private Boolean vazecaLicna;

    @Column(name = "sudskoUverenje", unique = false, nullable = false)
    private Boolean sudskoUverenje;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "list_id", referencedColumnName = "id")
    private OruzniList list;
}
