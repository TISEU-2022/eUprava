package com.eUprava.eUprava.service;

import com.eUprava.eUprava.model.dto.OruzijeDTO;
import com.eUprava.eUprava.model.entity.Oruzije;
import com.eUprava.eUprava.payload.OruzijePostRequest;

import java.util.List;

public interface OruzijeService {

    Oruzije findOne(Long oruzijeId);
    List<Oruzije> findAll();
    Oruzije save(OruzijeDTO oruzijeDTO);
    void remove (Long id);
    Oruzije update(Long id, OruzijePostRequest oruzijePostRequest);
}
