package com.projekat.predskolsko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekat.predskolsko.model.Roditelj;

public interface RoditeljRepository extends JpaRepository<Roditelj, Integer> {

	Roditelj findRoditeljById(Integer id);

	List<Roditelj> findAll();
	
}
