package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.IzvestajDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Izvestaj;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Sluzbenik;

public class IzvestajMapper {

    public static Izvestaj mapModel(IzvestajDTO izvestajDTO){
        return Izvestaj.builder()
                .id(izvestajDTO.getId())
                .izvestaj(izvestajDTO.getIzvestaj())
                .prihvaceno(izvestajDTO.getPrihvaceno())
                .vremePodnosenja(izvestajDTO.getVremePodnosenja())
                .sluzbenik(SluzbenikMapper.mapModel(izvestajDTO.getSluzbenikDTO()))
                .build();
    }

    public static IzvestajDTO mapDTO(Izvestaj izvestaj){
        return IzvestajDTO.builder()
                .id(izvestaj.getId())
                .izvestaj(izvestaj.getIzvestaj())
                .prihvaceno(izvestaj.getPrihvaceno())
                .vremePodnosenja(izvestaj.getVremePodnosenja())
                .sluzbenikDTO(SluzbenikMapper.mapDTO(izvestaj.getSluzbenik()))
                .build();
    }
}
