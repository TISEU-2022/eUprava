package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.PredstavkaRequestDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.PredstavkaResponseDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Predstavka;
import com.ftn.KomunalnaPolicijaIInspekcija.model.VrstaPredstavke;

public class PredstavkaMapper {

    public static Predstavka mapModel(PredstavkaRequestDTO predstavkaRequestDTO){

        VrstaPredstavke vrstaPredstavke = new VrstaPredstavke();
        vrstaPredstavke.setId(predstavkaRequestDTO.getVrstaPredstavkeId());

        Podnosilac podnosilac = new Podnosilac();
        podnosilac.setIme(predstavkaRequestDTO.getIme());
        podnosilac.setPrezime(predstavkaRequestDTO.getPrezime());
        podnosilac.setJmbg(predstavkaRequestDTO.getJmbg());
        podnosilac.setEmail(predstavkaRequestDTO.getEmail());
        podnosilac.setBrojTelefona(predstavkaRequestDTO.getTelefon());
        podnosilac.setAdresa(predstavkaRequestDTO.getAdresa());
        podnosilac.setMesto(predstavkaRequestDTO.getMesto());
        podnosilac.setPttBroj(predstavkaRequestDTO.getPttBroj());


        return Predstavka.builder()
                .naslov(predstavkaRequestDTO.getNaslov())
                .opis(predstavkaRequestDTO.getOpis())
                .adresaDogadjaja(predstavkaRequestDTO.getAdresaDogadjaja())
                .mestoDogadjaja(predstavkaRequestDTO.getMestoDogadjaja())
                .datumDogadjaja(predstavkaRequestDTO.getDatumDogadjaja())
                .datoteke(predstavkaRequestDTO.getDatoteke())
                .vrstaPredstavke(vrstaPredstavke)
                .podnosilac(podnosilac)
                .build();
    }

    public static PredstavkaResponseDTO mapDTO(Predstavka predstavka){

        return PredstavkaResponseDTO.builder()
                .id(predstavka.getId())
                .naslov(predstavka.getNaslov())
                .opis(predstavka.getOpis())
                .vremePodnosenja(predstavka.getVremePodnosenja())
                .adresaDogadjaja(predstavka.getAdresaDogadjaja())
                .mestoDogadjaja(predstavka.getMestoDogadjaja())
                .datumDogadjaja(predstavka.getDatumDogadjaja())
                .datoteke(predstavka.getDatoteke())
                .vrstaPredstavke(VrstaPredstavkeMapper.mapDTO(predstavka.getVrstaPredstavke()))
                .izvestaj(predstavka.getIzvestaj() != null ? IzvestajMapper.mapDTO(predstavka.getIzvestaj())  : null)
                .podnosilac(PodnosilacMapper.mapDTO(predstavka.getPodnosilac()))
                .build();
    }
}
