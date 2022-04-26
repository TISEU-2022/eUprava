package com.ftn.glasanjebackend.model;

import com.ftn.glasanjebackend.model.enumeration.ETipIzbora;

import java.util.Date;
import java.util.List;

public class Izbori {

    long id;
    String naziv;
    Date datum;
    List<Kandidat> kandidati;
    ETipIzbora tipIzbora;
    //EOpstina opstina;

}