package com.projekat.predskolsko.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projekat.predskolsko.model.Roditelj;

@Service
public interface RoditeljService {
	
	List<Roditelj> findAll();
	
	Roditelj findOne(Integer id);
	
	void remove(Integer id);
	
	Roditelj save(Roditelj soditelj);

}
