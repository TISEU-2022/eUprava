package com.eUprava.eUprava.repository;

import com.eUprava.eUprava.model.entity.OruzniList;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface OruzniListRepository {

    Optional<Object> findOne(Long Id);

    List<OruzniList> findAll();

    OruzniList save(OruzniList oruzniList);

    void deleteById(Long id);

    Optional<Object> findById(Long id);
}
