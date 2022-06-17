package com.projekat.predskolsko.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekat.predskolsko.model.Roditelj;
import com.projekat.predskolsko.repository.RoditeljRepository;
import com.projekat.predskolsko.services.RoditeljService;

@Service
public class RoditeljServiceImpl implements RoditeljService{

	@Autowired
	private RoditeljRepository roditeljkRep;
	
	
	@Override
	public List<Roditelj> findAll() {
		return roditeljkRep.findAll();
	}

	@Override
	public Roditelj findOne(Integer id) {
		return roditeljkRep.findRoditeljById(id);
	}

	@Override
	public void remove(Integer id) {
		roditeljkRep.deleteById(id);
		
	}

	@Override
	public Roditelj save(Roditelj roditelj) {
		return roditeljkRep.save(roditelj);
	}

}
