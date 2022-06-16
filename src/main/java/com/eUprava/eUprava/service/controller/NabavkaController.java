package com.eUprava.eUprava.service.controller;

import com.eUprava.eUprava.model.dto.NabavkaDTO;
import com.eUprava.eUprava.model.entity.ZahtevZaNabavku;
import com.eUprava.eUprava.payload.NabavkaPostRequest;
import com.eUprava.eUprava.service.NabavkaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/nabavke")
@CrossOrigin(origins = "http://localhost:8080")
public class NabavkaController {


    private final NabavkaService nabavkaService;

    NabavkaController(NabavkaService nabavkaService) {
        this.nabavkaService = nabavkaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZahtevZaNabavku> findOne(@PathVariable Long id) {
        ZahtevZaNabavku zahtevZaNabavku =  nabavkaService.findById(id);

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

        nabavkaService.remove(id);

        return ResponseEntity.noContent().build();
    }
}
