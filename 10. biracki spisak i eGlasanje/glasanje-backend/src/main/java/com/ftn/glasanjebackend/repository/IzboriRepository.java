package com.ftn.glasanjebackend.repository;

import com.ftn.glasanjebackend.model.Izbori;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IzboriRepository extends JpaRepository<Izbori, Long> {

    List<Izbori> findAll();

}
