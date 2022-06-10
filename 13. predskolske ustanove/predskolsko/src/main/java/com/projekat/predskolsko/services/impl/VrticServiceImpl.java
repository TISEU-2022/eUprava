package com.projekat.predskolsko.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekat.predskolsko.model.Vrtic;
import com.projekat.predskolsko.repository.VrticRepository;
import com.projekat.predskolsko.services.VrticService;

@Service
public class VrticServiceImpl implements VrticService {
	
	@Autowired
	private VrticRepository vrticRepository;

	@Override
	public List<Vrtic> findAll() {
		return vrticRepository.findAll();
	}

	@Override
	public Vrtic findOne(Integer id) {
		return vrticRepository.findVrticById(id);
	}

	@Override
	public void remove(Integer id) {
		vrticRepository.deleteById(id);
	}

	@Override
	public Vrtic save(Vrtic vrtic) {
		return vrticRepository.save(vrtic);
	}
	
	
	
}
