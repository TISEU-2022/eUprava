package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.KomunalniProblemDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.KomunalniProblemRequestDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.KomunalniProblem;
import com.ftn.KomunalnaPolicijaIInspekcija.model.VrstaKomunalnogProblema;

public class KomunalniProblemMapper {

    public static KomunalniProblem mapModel(KomunalniProblemRequestDTO komunalniProblemRequestDTO){

        VrstaKomunalnogProblema vrstaKomunalnogProblema = new VrstaKomunalnogProblema();
        vrstaKomunalnogProblema.setId(komunalniProblemRequestDTO.getVrstaKomunalnogProblemaId());

        return KomunalniProblem.builder()
                .opis(komunalniProblemRequestDTO.getOpis())
                .adresaDogadjaja(komunalniProblemRequestDTO.getAdresaDogadjaja())
                .mestoDogadjaja(komunalniProblemRequestDTO.getMestoDogadjaja())
                .datumDogadjaja(komunalniProblemRequestDTO.getDatumDogadjaja())
                .vrstaKomunalnogProblema(vrstaKomunalnogProblema)
                .datoteke(komunalniProblemRequestDTO.getDatoteke())
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
                .vrstaKomunalnogProblema(komunalniProblem.getVrstaKomunalnogProblema())
                .datoteke(komunalniProblem.getDatoteke())
                .build();
    }
}
