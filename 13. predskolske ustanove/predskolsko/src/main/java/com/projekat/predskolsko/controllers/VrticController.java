package com.projekat.predskolsko.controllers;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

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

import com.projekat.predskolsko.model.Vrtic;
import com.projekat.predskolsko.services.VrticService;


@Controller
@RequestMapping(value = "api/vrtic")
@CrossOrigin(origins = "http://localhost:4200")
public class VrticController {

	@Autowired
	private VrticService vrticService;

	@GetMapping
	public ResponseEntity<List<Vrtic>> getVrtici() {
		List<Vrtic> vrtici = vrticService.findAll();

		return new ResponseEntity<>(vrtici, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Vrtic> getVrtici(@PathVariable("id") Integer id) {
		Vrtic vrtic = vrticService.findOne(id);
		if (vrtic == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(vrtic, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteVrtic(@PathVariable("id") Integer id) {
		Vrtic vrtic = vrticService.findOne(id);

		if (!vrtic.isDeleted()) {
			vrtic.setDeleted(true);
			vrticService.save(vrtic);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Vrtic> updateVrtic(@PathVariable(value = "id", required = false) final Integer id,
			@RequestBody Vrtic vrtic) throws URISyntaxException {
		if (vrtic.getVrtic_id() == null) {
			System.out.println("");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (!Objects.equals(id, vrtic.getVrtic_id())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if (vrticService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Vrtic result = vrticService.save(vrtic);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping()
	public ResponseEntity<Vrtic> save(@RequestBody Vrtic vrtic) {
		Vrtic newVrtic = vrticService.save(vrtic);
		return ResponseEntity.status(201).body(newVrtic);
	}

}