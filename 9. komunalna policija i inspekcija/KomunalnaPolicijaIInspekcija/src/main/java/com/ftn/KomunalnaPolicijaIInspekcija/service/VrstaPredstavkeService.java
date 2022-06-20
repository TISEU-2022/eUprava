package com.ftn.KomunalnaPolicijaIInspekcija.service;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.VrstaPredstavkeDTO;

import java.util.List;

public interface VrstaPredstavkeService {
    VrstaPredstavkeDTO getOne(Long id);
    List<VrstaPredstavkeDTO> findAll();
    Long save(VrstaPredstavkeDTO vrstaPredstavkeDTO);
    void update(Long id, VrstaPredstavkeDTO vrstaPredstavkeDTO);
    boolean delete(Long id);
}
