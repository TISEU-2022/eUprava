package com.ftn.KomunalnaPolicijaIInspekcija.service.implementation;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.PredstavkaResponseDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.PredstavkaMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Predstavka;
import com.ftn.KomunalnaPolicijaIInspekcija.repository.PredstavkaRepository;
import com.ftn.KomunalnaPolicijaIInspekcija.service.PredstavkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PredstavkaServiceImpl implements PredstavkaService {

    @Autowired
    private PredstavkaRepository predstavkaRepository;

    @Override
    public List<PredstavkaResponseDTO> findAll() {
        List<Predstavka> predstavke = predstavkaRepository.findAll();
        List<PredstavkaResponseDTO> dtos = new ArrayList<>();
        for(Predstavka p : predstavke){
            dtos.add(PredstavkaMapper.mapDTO(p));
        }
        return dtos;
    }

    @Override
    public List<PredstavkaResponseDTO> findByPodnosilac(Podnosilac podnosilac) {
        List<Predstavka> predstavke = predstavkaRepository.findPredstavkasByPodnosilac(podnosilac);
        List<PredstavkaResponseDTO> dtos = new ArrayList<>();
        for(Predstavka p : predstavke){
            dtos.add(PredstavkaMapper.mapDTO(p));
        }
        return dtos;
    }

    @Override
    public Predstavka findOne(Long id) {
        return predstavkaRepository.findPredstavkaById(id);
    }

    @Override
    public Predstavka save(Predstavka predstavka) {
        return predstavkaRepository.save(predstavka);
    }
}
