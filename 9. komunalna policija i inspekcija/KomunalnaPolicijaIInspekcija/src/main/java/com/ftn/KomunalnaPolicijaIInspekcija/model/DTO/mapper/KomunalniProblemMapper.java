package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.KomunalniProblemDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.KomunalniProblem;

public class KomunalniProblemMapper {

    public static KomunalniProblem mapModel(KomunalniProblemDTO komunalniProblemDTO){
        return KomunalniProblem.builder()
                .id(komunalniProblemDTO.getId())
                .opis(komunalniProblemDTO.getOpis())
                .adresaDogadjaja(komunalniProblemDTO.getAdresaDogadjaja())
                .mestoDogadjaja(komunalniProblemDTO.getMestoDogadjaja())
                .datumDogadjaja(komunalniProblemDTO.getDatumDogadjaja())
                .datumPodnosenja(komunalniProblemDTO.getDatumPodnosenja())
                .build();
    }

    public static KomunalniProblemDTO mapDTO(KomunalniProblem komunalniProblem){
        return KomunalniProblemDTO.builder()
                .id(komunalniProblem.getId())
                .opis(komunalniProblem.getOpis())
                .adresaDogadjaja(komunalniProblem.getAdresaDogadjaja())
                .mestoDogadjaja(komunalniProblem.getMestoDogadjaja())
                .datumDogadjaja(komunalniProblem.getDatumDogadjaja())
                .datumPodnosenja(komunalniProblem.getDatumPodnosenja())
                .build();
    }
}
