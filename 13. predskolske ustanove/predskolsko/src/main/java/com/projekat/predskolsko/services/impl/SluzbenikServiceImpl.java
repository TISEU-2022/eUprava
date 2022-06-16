package com.projekat.predskolsko.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekat.predskolsko.model.Sluzbenik;
import com.projekat.predskolsko.repository.SluzbenikRepository;
import com.projekat.predskolsko.services.SluzbenikService;
@Service
public class SluzbenikServiceImpl implements SluzbenikService{

	@Autowired
	private SluzbenikRepository sluzbenikRep;
	
	
	@Override
	public List<Sluzbenik> findAll() {
		return sluzbenikRep.findAll();
	}

	@Override
	public Sluzbenik findOne(Integer id) {
		return sluzbenikRep.findSluzbenikById(id);
	}

	@Override
	public void remove(Integer id) {
		sluzbenikRep.deleteById(id);
		
	}

	@Override
	public Sluzbenik save(Sluzbenik sluzbenik) {
		return sluzbenikRep.save(sluzbenik);
	}

}
