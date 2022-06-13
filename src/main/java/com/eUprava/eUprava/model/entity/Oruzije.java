package com.eUprava.eUprava.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private OruzniList list;

    @JsonIgnore
    @OneToOne(mappedBy = "oruzije")
    private ZahtevZaNabavku zahtev;

}
