package com.eUprava.eUprava.controller;
import com.eUprava.eUprava.exeptions.NosenjeNotFoundExeption;
import com.eUprava.eUprava.model.dto.NosenjeDTO;
import com.eUprava.eUprava.model.entity.ZahtevZaNosenje;
import com.eUprava.eUprava.payload.NosenjePostRequest;
import com.eUprava.eUprava.repository.NosenjeRepository;
import com.eUprava.eUprava.service.NosenjeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/nosenje")
@CrossOrigin(origins = "http://localhost:4200")
public class NosenjeController {

    private final NosenjeRepository nosenjeRepository;
    private final NosenjeService nosenjeService;

    NosenjeController(NosenjeRepository nosenjeRepository,NosenjeService nosenjeService) {
        this.nosenjeRepository = nosenjeRepository;
        this.nosenjeService = nosenjeService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ZahtevZaNosenje> findOne(@PathVariable Long nosenje_id) {
        ZahtevZaNosenje zahtevZaNosenje = (ZahtevZaNosenje) nosenjeRepository.findById(nosenje_id)
                .orElseThrow(() -> new NosenjeNotFoundExeption("Artikal sa datim id ne postoji"));

        return new ResponseEntity<>(zahtevZaNosenje, HttpStatus.FOUND);
    }
    @GetMapping
    public ResponseEntity<List<ZahtevZaNosenje>> findAll() {
        List<ZahtevZaNosenje> zahtevZaNosenje = nosenjeService.findAll();
        return new ResponseEntity<>(zahtevZaNosenje, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<ZahtevZaNosenje> save(@Valid @RequestBody NosenjeDTO nosenjeDTO) {
        ZahtevZaNosenje zahtevZaNosenje = nosenjeService.save(nosenjeDTO);
        return new ResponseEntity<>(zahtevZaNosenje, HttpStatus.CREATED);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ZahtevZaNosenje> update(@PathVariable("id") Long nosenje_id,
                                                  @Valid @RequestBody NosenjePostRequest nosenjePostRequest) {
        ZahtevZaNosenje update = nosenjeService.update(nosenje_id, nosenjePostRequest);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteNosenje(@PathVariable Long id) {

        nosenjeRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
