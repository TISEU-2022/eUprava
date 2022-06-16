package com.projekat.predskolsko.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projekat.predskolsko.model.Sluzbenik;
import com.projekat.predskolsko.services.SluzbenikService;

@Controller
@RequestMapping(value = "api/sluzbenik")
@CrossOrigin(origins = "http://localhost:4200")
public class SluzbenikController {
	@Autowired
	private SluzbenikService sluzbenikSer;

	@GetMapping
	public ResponseEntity<List<Sluzbenik>> getSluzbenike() {
		List<Sluzbenik> sluzbenici = sluzbenikSer.findAll();

		return new ResponseEntity<>(sluzbenici, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Sluzbenik> getSluzbenik(@PathVariable("id") Integer id) {
		Sluzbenik sluzbenik = sluzbenikSer.findOne(id);
		if (sluzbenik == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(sluzbenik, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteSluzbenik(@PathVariable("id") Integer id) {
		Sluzbenik sluzbenik = sluzbenikSer.findOne(id);

		if (!sluzbenik.isDeleted()) {
			sluzbenik.setDeleted(true);
			sluzbenikSer.save(sluzbenik);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Sluzbenik> save(@RequestBody Sluzbenik sluzbenik) {
		Sluzbenik newSluzbenik = sluzbenikSer.save(sluzbenik);
		return ResponseEntity.status(201).body(newSluzbenik);
	}

}
