package com.eUprava.eUprava.repository;

import com.eUprava.eUprava.model.entity.Oruzije;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OruzijeRepository {
    Optional<Object> findOne(Long oruzijeId);

    List<Oruzije> findAll();

    Oruzije save(Oruzije oruzije);

    void deleteById(Long id);

    Optional<Object> findById(Long id);
}
