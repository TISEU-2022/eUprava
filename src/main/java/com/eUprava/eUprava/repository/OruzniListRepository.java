package com.eUprava.eUprava.repository;

import com.eUprava.eUprava.model.entity.OruzniList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OruzniListRepository extends JpaRepository<OruzniList, Long> {

    //Optional<OruzniList> findOne(Long Id);


    OruzniList save(OruzniList oruzniList);

    void deleteById(Long id);

    Optional<OruzniList> findById(Long id);
}
