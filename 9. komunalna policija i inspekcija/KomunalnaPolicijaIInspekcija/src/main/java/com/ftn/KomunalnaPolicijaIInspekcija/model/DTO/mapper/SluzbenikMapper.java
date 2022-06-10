package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.SluzbenikDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Sluzbenik;

public class SluzbenikMapper {

    public static Sluzbenik mapModel(SluzbenikDTO sluzbenikDTO){
        return Sluzbenik.builder()
                .id(sluzbenikDTO.getId())
                .ime(sluzbenikDTO.getIme())
                .prezime(sluzbenikDTO.getPrezime())
                .email(sluzbenikDTO.getEmail())
                .jmbg(sluzbenikDTO.getJmbg())
                .build();
    }

    public static SluzbenikDTO mapDTO(Sluzbenik sluzbenik){
        return SluzbenikDTO.builder()
                .id(sluzbenik.getId())
                .ime(sluzbenik.getIme())
                .prezime(sluzbenik.getPrezime())
                .email(sluzbenik.getEmail())
                .jmbg(sluzbenik.getJmbg())
                .build();
    }

}
