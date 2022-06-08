package com.eUprava.eUprava.controller;

import com.eUprava.eUprava.exeptions.OruzijeNotFoundExeption;
import com.eUprava.eUprava.model.dto.OruzijeDTO;
import com.eUprava.eUprava.model.entity.Oruzije;
import com.eUprava.eUprava.payload.OruzijePostRequest;
import com.eUprava.eUprava.repository.OruzijeRepository;
import com.eUprava.eUprava.service.OruzijeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/oruzije")
@CrossOrigin(origins = "http://localhost:4200")
public class OruzijeController {

    private final OruzijeRepository oruzijeRepository;
    private final OruzijeService oruzijeService;

    OruzijeController(OruzijeRepository oruzijeRepository,OruzijeService oruzijeService) {
        this.oruzijeRepository = oruzijeRepository;
        this.oruzijeService = oruzijeService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Oruzije> findOne(@PathVariable Long id) {
        Oruzije oruzije = (Oruzije) oruzijeRepository.findById(id)
                .orElseThrow(() -> new OruzijeNotFoundExeption("Artikal sa datim id ne postoji"));

        return new ResponseEntity<>(oruzije, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Oruzije>> findAll() {
        List<Oruzije> oruzije = oruzijeService.findAll();
        return new ResponseEntity<>(oruzije, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Oruzije> save(@Valid @RequestBody OruzijeDTO oruzijeDTO) {
        Oruzije oruzije = oruzijeService.save(oruzijeDTO);
        return new ResponseEntity<>(oruzije, HttpStatus.CREATED);

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Oruzije> update(@PathVariable("id") Long id,
                                          @Valid @RequestBody OruzijePostRequest oruzijePostRequest) {
        Oruzije update = oruzijeService.update(id, oruzijePostRequest);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteOruzije(@PathVariable Long id) {

        oruzijeRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
