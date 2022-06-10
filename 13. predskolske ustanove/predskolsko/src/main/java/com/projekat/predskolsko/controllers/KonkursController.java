package com.projekat.predskolsko.controllers;

import java.net.URISyntaxException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projekat.predskolsko.model.Konkurs;
import com.projekat.predskolsko.services.KonkursService;


@Controller
@RequestMapping(value = "api/konkurs")
@CrossOrigin(origins = "http://localhost:4200")
public class KonkursController {
	
	@Autowired
	private KonkursService konkursService;

	@GetMapping
	public ResponseEntity<List<Konkurs>> getKonkursi() {
		List<Konkurs> konkursi = konkursService.findAll();

		return new ResponseEntity<>(konkursi, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Konkurs> getKonkursi(@PathVariable("id") Integer id) {
		Konkurs konkurs = konkursService.findOne(id);
		if (konkurs == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(konkurs, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteKonkurs(@PathVariable("id") Integer id) {
		Konkurs konkurs = konkursService.findOne(id);

		if (!konkurs.isDeleted()) {
			konkurs.setDeleted(true);
			konkursService.save(konkurs);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Konkurs> updateKonkurs(@PathVariable(value = "id", required = false) final Integer id,
			@RequestBody Konkurs konkurs) throws URISyntaxException {
		if (konkursService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Konkurs result = konkursService.save(konkurs);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping()
	public ResponseEntity<Konkurs> save(@RequestBody Konkurs konkurs) {
		Konkurs newKonkurs = konkursService.save(konkurs);
		return ResponseEntity.status(201).body(newKonkurs);
	}
	
}
