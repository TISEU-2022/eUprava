package com.ftn.glasanjebackend.model;

import com.ftn.glasanjebackend.model.enumeration.ETipIzbora;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Izbori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naziv;
    private Date datum;
    @OneToMany
    private List<Kandidat> kandidati;
    private ETipIzbora tipIzbora;
    //EOpstina opstina;

}