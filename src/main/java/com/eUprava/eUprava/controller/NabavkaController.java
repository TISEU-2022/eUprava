package com.eUprava.eUprava.controller;

import com.eUprava.eUprava.exeptions.NabavkaNotFoundExeption;
import com.eUprava.eUprava.exeptions.OruzijeNotFoundExeption;
import com.eUprava.eUprava.model.dto.NabavkaDTO;
import com.eUprava.eUprava.model.dto.OruzijeDTO;
import com.eUprava.eUprava.model.entity.Oruzije;
import com.eUprava.eUprava.model.entity.ZahtevZaNabavku;
import com.eUprava.eUprava.payload.NabavkaPostRequest;
import com.eUprava.eUprava.payload.OruzijePostRequest;
import com.eUprava.eUprava.repository.NabavkaRepository;
import com.eUprava.eUprava.repository.OruzijeRepository;
import com.eUprava.eUprava.service.NabavkaService;
import com.eUprava.eUprava.service.OruzijeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/nabavke")
@CrossOrigin(origins = "http://localhost:4200")
public class NabavkaController {

    private final NabavkaRepository nabavkaRepository;
    private final NabavkaService nabavkaService;

    NabavkaController(NabavkaRepository nabavkaRepository,NabavkaService nabavkaService) {
        this.nabavkaRepository = nabavkaRepository;
        this.nabavkaService = nabavkaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZahtevZaNabavku> findOne(@PathVariable Long id) {
        ZahtevZaNabavku zahtevZaNabavku = (ZahtevZaNabavku) nabavkaRepository.findById(id)
                .orElseThrow(() -> new NabavkaNotFoundExeption("Artikal sa datim id ne postoji"));

        return new ResponseEntity<>(zahtevZaNabavku, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ZahtevZaNabavku>> findAll() {
        List<ZahtevZaNabavku> zahtevZaNabavkus = nabavkaService.findAll();
        return new ResponseEntity<>(zahtevZaNabavkus, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ZahtevZaNabavku> save(@Valid @RequestBody NabavkaDTO nabavkaDTO) {
        ZahtevZaNabavku zahtevZaNabavku = nabavkaService.save(nabavkaDTO);
        return new ResponseEntity<>(zahtevZaNabavku, HttpStatus.CREATED);

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ZahtevZaNabavku> update(@PathVariable("id") Long id,
                                          @Valid @RequestBody NabavkaPostRequest nabavkaPostRequest) {
        ZahtevZaNabavku update = nabavkaService.update(id, nabavkaPostRequest);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteNabavku(@PathVariable Long id) {

        nabavkaRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
