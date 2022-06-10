package com.projekat.predskolsko.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projekat.predskolsko.model.Dete;
import com.projekat.predskolsko.model.Konkurs;
import com.projekat.predskolsko.services.DeteService;

@Controller
@RequestMapping(value="/dete")
public class DeteController {
	
	@Autowired
	private DeteService deteSer;
	
	@GetMapping
	public ResponseEntity<List<Dete>> getDeca() {
		List<Dete> deca = deteSer.findAll();

		return new ResponseEntity<>(deca, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Dete> getDeca(@PathVariable("id") Integer id) {
		Dete dete = deteSer.findOne(id);
		if (dete == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dete, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteKonkurs(@PathVariable("id") Integer id) {
		Dete dete = deteSer.findOne(id);

		if (!dete.isDeleted()) {
			dete.setDeleted(true);
			deteSer.save(dete);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Dete> save(@RequestBody Dete dete) {
		Dete newDete = deteSer.save(dete);
		return ResponseEntity.status(201).body(newDete);
	}
	
}
