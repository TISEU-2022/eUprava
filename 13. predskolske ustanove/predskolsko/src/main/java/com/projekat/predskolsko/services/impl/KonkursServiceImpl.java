package com.projekat.predskolsko.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekat.predskolsko.model.Konkurs;
import com.projekat.predskolsko.repository.KonkursRepository;
import com.projekat.predskolsko.services.KonkursService;

@Service
public class KonkursServiceImpl implements KonkursService{
	
	@Autowired
	private KonkursRepository konkursRepository;

	@Override
	public List<Konkurs> findAll() {
		return konkursRepository.findAll();
	}

	@Override
	public Konkurs findOne(Integer id) {
		return konkursRepository.findKonkursById(id);
	}

	@Override
	public void remove(Integer id) {
		konkursRepository.deleteById(id);
	}

	@Override
	public Konkurs save(Konkurs konkurs) {
		return konkursRepository.save(konkurs);
	}

}
