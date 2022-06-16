package com.projekat.predskolsko.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekat.predskolsko.model.Dete;
import com.projekat.predskolsko.repository.DeteRepository;
import com.projekat.predskolsko.services.DeteService;

@Service
public class DeteServiceImpl implements DeteService{
	
	@Autowired
	private DeteRepository deteRep;

	@Override
	public List<Dete> findAll() {
		return deteRep.findAll();
	}

	@Override
	public Dete findOne(Integer id) {
		return deteRep.findDeteById(id);
	}

	@Override
	public void remove(Integer id) {
		deteRep.deleteById(id);
		
	}

	@Override
	public Dete save(Dete dete) {
		return deteRep.save(dete);
	}

}
