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

import com.projekat.predskolsko.model.Roditelj;
import com.projekat.predskolsko.services.RoditeljService;

@Controller
@RequestMapping(value = "api/roditelj")
@CrossOrigin(origins = "http://localhost:4200")
public class RoditeljController {
	@Autowired
	private RoditeljService roditeljSer;

	@GetMapping
	public ResponseEntity<List<Roditelj>> getRoditelje() {
		List<Roditelj> sluzbenici = roditeljSer.findAll();

		return new ResponseEntity<>(sluzbenici, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Roditelj> getRoditelj(@PathVariable("id") Integer id) {
		Roditelj roditelj = roditeljSer.findOne(id);
		if (roditelj == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(roditelj, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteRoditelj(@PathVariable("id") Integer id) {
		Roditelj roditelj = roditeljSer.findOne(id);

		if (!roditelj.isBlocked()) {
			roditelj.setBlocked(true);
			roditeljSer.save(roditelj);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Roditelj> save(@RequestBody Roditelj roditelj) {
		Roditelj newRoditelj = roditeljSer.save(roditelj);
		return ResponseEntity.status(201).body(newRoditelj);
	}

}

