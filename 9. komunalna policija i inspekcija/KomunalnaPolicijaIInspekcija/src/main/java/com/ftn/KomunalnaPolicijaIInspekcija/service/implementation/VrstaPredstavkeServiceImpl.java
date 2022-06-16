package com.ftn.KomunalnaPolicijaIInspekcija.service.implementation;

import com.ftn.KomunalnaPolicijaIInspekcija.exception.NotFoundException;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.VrstaPredstavkeDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.VrstaPredstavkeMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.VrstaPredstavke;
import com.ftn.KomunalnaPolicijaIInspekcija.repository.VrstaPredstavkeRepository;
import com.ftn.KomunalnaPolicijaIInspekcija.service.VrstaPredstavkeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VrstaPredstavkeServiceImpl implements VrstaPredstavkeService {

    @Autowired
    private VrstaPredstavkeRepository vrstaPredstavkeRepository;

    @Override
    public VrstaPredstavkeDTO getOne(Long id) throws NotFoundException{
        VrstaPredstavke vrstaPredstavke = vrstaPredstavkeRepository.findVrstaPredstavkeById(id);
        if (vrstaPredstavke == null){
            throw new NotFoundException("VrstaPredstavke with id {id} not found.");
        }
        return VrstaPredstavkeMapper.mapDTO(vrstaPredstavke);
    }

    @Override
    public List<VrstaPredstavkeDTO> findAll() {
        List<VrstaPredstavke> vrstaPredstavkes = vrstaPredstavkeRepository.findAll();
        List<VrstaPredstavkeDTO> vrstaPredstavkeDTOS = new ArrayList<>();
        for (VrstaPredstavke vp: vrstaPredstavkes) {
            vrstaPredstavkeDTOS.add(VrstaPredstavkeMapper.mapDTO(vp));
        }
        return vrstaPredstavkeDTOS;
    }

    @Override
    public Long save(VrstaPredstavkeDTO vrstaPredstavkeDTO) {
        VrstaPredstavke vrstaPredstavke = vrstaPredstavkeRepository.save(VrstaPredstavkeMapper.mapModel(vrstaPredstavkeDTO));
        return vrstaPredstavke.getId();
    }

    @Override
    public void update(Long id, VrstaPredstavkeDTO vrstaPredstavkeDTO) {
        VrstaPredstavke vrstaPredstavke = vrstaPredstavkeRepository.findVrstaPredstavkeById(id);
        if (vrstaPredstavke == null){
            throw new NotFoundException("VrstaPredstavke with id {id} not found.");
        }
        vrstaPredstavkeDTO.setId(id);
        vrstaPredstavkeRepository.save(VrstaPredstavkeMapper.mapModel(vrstaPredstavkeDTO));
    }

    @Override
    public boolean delete(Long id) {
        VrstaPredstavke vrstaPredstavke = vrstaPredstavkeRepository.findVrstaPredstavkeById(id);
        if (vrstaPredstavke == null){
            return false;
        }
        vrstaPredstavkeRepository.delete(vrstaPredstavke);
        return true;
    }
}
