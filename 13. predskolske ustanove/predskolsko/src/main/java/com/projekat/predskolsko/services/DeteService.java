package com.projekat.predskolsko.services;

import java.util.List;
import com.projekat.predskolsko.model.Dete;

public interface DeteService {

	List<Dete> findAll();
	
	Dete findOne(Integer id);
	
	void remove(Integer id);
	
	Dete save(Dete dete);
	
}
