package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.PodnosilacDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;

public class PodnosilacMapper {

    public static Podnosilac mapModel(PodnosilacDTO podnosilacDTO){
        return Podnosilac.builder()
                .ime(podnosilacDTO.getIme())
                .prezime(podnosilacDTO.getPrezime())
                .jmbg(podnosilacDTO.getJmbg())
                .adresa(podnosilacDTO.getAdresa())
                .brojTelefona(podnosilacDTO.getTelefon())
                .email(podnosilacDTO.getEmail())
                .pttBroj(podnosilacDTO.getPttBroj())
                .build();
    }

    public static PodnosilacDTO mapDTO(Podnosilac podnosilac){
        return PodnosilacDTO.builder()
                .ime(podnosilac.getIme())
                .prezime(podnosilac.getPrezime())
                .jmbg(podnosilac.getJmbg())
                .adresa(podnosilac.getAdresa())
                .telefon(podnosilac.getBrojTelefona())
                .email(podnosilac.getEmail())
                .pttBroj(podnosilac.getPttBroj())
                .build();
    }
}
