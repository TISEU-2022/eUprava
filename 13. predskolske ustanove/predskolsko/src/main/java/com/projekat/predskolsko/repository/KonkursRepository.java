package com.projekat.predskolsko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projekat.predskolsko.model.Konkurs;


@Repository
public interface KonkursRepository extends JpaRepository<Konkurs, Integer>{

	Konkurs findKonkursById(Integer id);
	
	List<Konkurs> findAll();
	
}