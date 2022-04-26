package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.PredstavkaDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Predstavka;

public class PredstavkaMapper {

    public static Predstavka mapModel(PredstavkaDTO predstavkaDTO){
        return Predstavka.builder()
                .id(predstavkaDTO.getId())
                .naslov(predstavkaDTO.getNaslov())
                .opis(predstavkaDTO.getOpis())
                .vremePodnosenja(predstavkaDTO.getVremePodnosenja())
                .adresaDogadjaja(predstavkaDTO.getAdresaDogadjaja())
                .mestoDogadjaja(predstavkaDTO.getMestoDogadjaja())
                .datumDogadjaja(predstavkaDTO.getDatumDogadjaja())
                .putanjeDoDatoteka(predstavkaDTO.getPutanjeDoDatoteka())
                .vrstaPredstavke(VrstaPredstavkeMapper.mapModel(predstavkaDTO.getVrstaPredstavkeDTO()))
                .izvestaj(IzvestajMapper.mapModel(predstavkaDTO.getIzvestajDTO()))
                .build();
    }

    public static PredstavkaDTO mapDTO(Predstavka predstavka){
        return PredstavkaDTO.builder()
                .id(predstavka.getId())
                .naslov(predstavka.getNaslov())
                .opis(predstavka.getOpis())
                .vremePodnosenja(predstavka.getVremePodnosenja())
                .adresaDogadjaja(predstavka.getAdresaDogadjaja())
                .mestoDogadjaja(predstavka.getMestoDogadjaja())
                .datumDogadjaja(predstavka.getDatumDogadjaja())
                .putanjeDoDatoteka(predstavka.getPutanjeDoDatoteka())
                .vrstaPredstavkeDTO(VrstaPredstavkeMapper.mapDTO(predstavka.getVrstaPredstavke()))
                .izvestajDTO(IzvestajMapper.mapDTO(predstavka.getIzvestaj()))
                .build();
    }
}
