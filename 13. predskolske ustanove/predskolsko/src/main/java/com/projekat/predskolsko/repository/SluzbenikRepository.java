package com.projekat.predskolsko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projekat.predskolsko.model.Sluzbenik;

@Repository
public interface SluzbenikRepository extends JpaRepository<Sluzbenik, Integer> {

	Sluzbenik findSluzbenikById(Integer id);

	List<Sluzbenik> findAll();
	
}
