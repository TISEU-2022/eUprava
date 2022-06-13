package com.eUprava.eUprava.repository;

import com.eUprava.eUprava.model.entity.Oruzije;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OruzijeRepository extends JpaRepository<Oruzije, Long> {
    //Optional<Oruzije> findOne(Long oruzijeId);

    Oruzije save(Oruzije oruzije);

    void deleteById(Long id);

    Optional<Oruzije> findById(Long id);
}
