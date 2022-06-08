package com.eUprava.eUprava.service.impl;

import com.eUprava.eUprava.model.entity.OruzniList;
import com.eUprava.eUprava.payload.OruzniListPostRequest;
import com.eUprava.eUprava.repository.OruzniListRepository;
import com.eUprava.eUprava.service.OruzniListService;
import com.eUprava.eUprava.model.dto.OruzniListDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class OruzniListServiceImpl implements OruzniListService {

    private final OruzniListRepository oruzniListRepository;

    public OruzniListServiceImpl(OruzniListRepository oruzniListRepository) {this.oruzniListRepository = oruzniListRepository;}

    @Override
    public OruzniList findOne(Long oruzniListId) {return (OruzniList) oruzniListRepository.findOne(oruzniListId).orElse(null);}

    @Override
    public List<OruzniList> findAll() {
        return oruzniListRepository.findAll();
    }

    @Override
    public OruzniList save(OruzniListDTO oruzniListDTO) {
        OruzniList oruzniList = new OruzniList();
        oruzniList.setImeVlasnika(oruzniListDTO.getImeVlasnika());
        oruzniList.setPrezimeVlasnika(oruzniListDTO.getPrezimeVlasnika());
        oruzniList.setDatumIzdavanja(oruzniListDTO.getDatumIzdavanja());
        oruzniList.setVaziDo(oruzniListDTO.getVaziDo());
        oruzniList.setRegistarskiBroj(oruzniListDTO.getRegistarskiBroj());
        oruzniList.setOruzije(oruzniListDTO.getOruzije());
        oruzniList.setZahtev(oruzniListDTO.getZahtev());
        return oruzniListRepository.save(oruzniList);
    }

    @Override
    public void remove(Long id) {
        oruzniListRepository.deleteById(id);
    }

    @Override
    public OruzniList update(Long id, OruzniListPostRequest oruzniListPostRequest) {
        OruzniList oruzniList = (OruzniList) oruzniListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Gun with id " + id + " not found"));


        oruzniList.setImeVlasnika(oruzniListPostRequest.getImeVlasnika());
        oruzniList.setPrezimeVlasnika(oruzniListPostRequest.getPrezimeVlasnika());
        oruzniList.setDatumIzdavanja(oruzniListPostRequest.getDatumIzdavanja());
        oruzniList.setVaziDo(oruzniListPostRequest.getVaziDo());
        oruzniList.setRegistarskiBroj(oruzniListPostRequest.getRegistarskiBroj());
        oruzniList.setOruzije(oruzniListPostRequest.getOruzije());
        oruzniList.setZahtev(oruzniListPostRequest.getZahtev());

        return oruzniListRepository.save(oruzniList);

    }

}
