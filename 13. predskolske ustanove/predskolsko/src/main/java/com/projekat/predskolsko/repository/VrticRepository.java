package com.projekat.predskolsko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekat.predskolsko.model.Vrtic;

public interface VrticRepository extends JpaRepository<Vrtic, Integer>{
	
	Vrtic findVrticById(Integer id);
	
	List<Vrtic> findAll();
	
}
