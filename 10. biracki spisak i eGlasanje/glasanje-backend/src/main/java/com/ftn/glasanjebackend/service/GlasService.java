package com.ftn.glasanjebackend.service;

import com.ftn.glasanjebackend.model.Glas;
import com.ftn.glasanjebackend.model.Izbori;
import com.ftn.glasanjebackend.repository.GlasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlasService {

    @Autowired
    GlasRepository glasRepository;

    public Glas save(Glas glas){
        return glasRepository.save(glas);
    }

    public List<Glas> findAll(){
        return glasRepository.findAll();
    }
//    public Glas findGlasByKorisnikIdAndIzboriId(Long korisnikId, Long izboriId){
//        return  glasRepository.findGlasByKorisnikIdAndIzboriId(korisnikId, izboriId);
//    }

    public List<Glas> findGlasByKandidatIdAndIzboriId(Long kandidatId, Long izboriId){
        return glasRepository.findGlasByKandidatIdAndIzboriId(kandidatId,izboriId);
    }
}
