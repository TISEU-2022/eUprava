package com.projekat.predskolsko.services;

import java.util.List;

import com.projekat.predskolsko.model.Vrtic;

public interface VrticService {

	List<Vrtic> findAll();
	
	Vrtic findOne(Integer id);
	
	void remove(Integer id);
	
	Vrtic save(Vrtic vrtic);
	
}
