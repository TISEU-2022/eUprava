package com.eUprava.eUprava.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "oruzije")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Oruzije implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oruzije_id", unique = true, nullable = false)
    private Long oruzije_id;

    @Column(name = "marka", unique = false, nullable = false)
    private String markaOruzija;

    @Column(name = "vrsta", unique = false, nullable = false)
    private String vrstaoruzija;

    @Column(name = "kalibar", unique = false, nullable = false)
    private String kalibar;

    @Column(name = "serijskiBroj", unique = true, nullable = false)
    private Long serijskiBroj;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "list_id", referencedColumnName = "id")
    private OruzniList list;

    @OneToOne(mappedBy = "oruzije")
    private ZahtevZaNabavku zahtev;

}
