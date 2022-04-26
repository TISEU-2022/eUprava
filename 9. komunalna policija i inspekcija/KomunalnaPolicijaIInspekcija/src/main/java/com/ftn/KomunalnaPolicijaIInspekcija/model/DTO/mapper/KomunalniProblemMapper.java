package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.KomunalniProblemDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.KomunalniProblem;

public class KomunalniProblemMapper {

    public static KomunalniProblem mapModel(KomunalniProblemDTO komunalniProblemDTO){
        return KomunalniProblem.builder()
                .opis(komunalniProblemDTO.getOpis())
                .adresaDogadjaja(komunalniProblemDTO.getAdresaDogadjaja())
                .mestoDogadjaja(komunalniProblemDTO.getMestoDogadjaja())
                .datumDogadjaja(komunalniProblemDTO.getDatumDogadjaja())
                .vrstaKomunalnogProblema(komunalniProblemDTO.getVrstaKomunalnogProblema())
                .datumPodnosenja(komunalniProblemDTO.getDatumPodnosenja())
                .podnosilac(PodnosilacMapper.mapModel(komunalniProblemDTO.getPodnosilacDTO()))
                .build();
    }

    public static KomunalniProblemDTO mapDTO(KomunalniProblem komunalniProblem){
        return KomunalniProblemDTO.builder()
                .opis(komunalniProblem.getOpis())
                .adresaDogadjaja(komunalniProblem.getAdresaDogadjaja())
                .mestoDogadjaja(komunalniProblem.getMestoDogadjaja())
                .datumDogadjaja(komunalniProblem.getDatumDogadjaja())
                .vrstaKomunalnogProblema(komunalniProblem.getVrstaKomunalnogProblema())
                .datumPodnosenja(komunalniProblem.getDatumPodnosenja())
                .podnosilacDTO(PodnosilacMapper.mapDTO(komunalniProblem.getPodnosilac()))
                .build();
    }
}
