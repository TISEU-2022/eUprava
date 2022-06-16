package com.projekat.predskolsko.services;

import java.util.List;

import com.projekat.predskolsko.model.Sluzbenik;

public interface SluzbenikService {

	List<Sluzbenik> findAll();
	
	Sluzbenik findOne(Integer id);
	
	void remove(Integer id);
	
	Sluzbenik save(Sluzbenik sluzbenik);
	
}
