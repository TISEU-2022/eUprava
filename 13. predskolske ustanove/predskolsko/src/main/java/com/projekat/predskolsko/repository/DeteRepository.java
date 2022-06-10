package com.projekat.predskolsko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projekat.predskolsko.model.Dete;

@Repository
public interface DeteRepository extends JpaRepository<Dete, Integer> {

	Dete findDeteById(Integer id);

	List<Dete> findAll();

}
