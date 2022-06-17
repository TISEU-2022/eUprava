package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.VrstaPredstavkeDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.VrstaPredstavke;

public class VrstaPredstavkeMapper {

    public static VrstaPredstavke mapModel(VrstaPredstavkeDTO vrstaPredstavkeDTO){
        return VrstaPredstavke.builder()
                .id(vrstaPredstavkeDTO.getId())
                .naziv(vrstaPredstavkeDTO.getNaziv())
                .build();
    }

    public static VrstaPredstavkeDTO mapDTO(VrstaPredstavke vrstaPredstavke){
        return VrstaPredstavkeDTO.builder()
                .id(vrstaPredstavke.getId())
                .naziv(vrstaPredstavke.getNaziv())
                .build();
    }
}
