package com.eUprava.eUprava.service;

import com.eUprava.eUprava.model.dto.OruzniListDTO;
import com.eUprava.eUprava.model.entity.Oruzije;
import com.eUprava.eUprava.model.entity.OruzniList;
import com.eUprava.eUprava.payload.OruzniListPostRequest;

import java.util.List;
public interface OruzniListService {
    OruzniList findById(Long oruzniListId);
    List<OruzniList> findAll();
    OruzniList save(OruzniListDTO oruzniListDTO);
    void remove (Long id);
    OruzniList update(Long id, OruzniListPostRequest oruzniListPostRequest);
}
