package com.projekat.predskolsko.services;

import java.util.List;

import com.projekat.predskolsko.model.Konkurs;

public interface KonkursService {
	
	List<Konkurs> findAll();
	
	Konkurs findOne(Integer id);
	
	void remove(Integer id);
	
	Konkurs save(Konkurs konkurs);
	
}
