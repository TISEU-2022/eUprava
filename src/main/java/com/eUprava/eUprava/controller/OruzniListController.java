package com.eUprava.eUprava.controller;
import com.eUprava.eUprava.exeptions.OruzniListNotFoundExeption;
import com.eUprava.eUprava.model.dto.OruzniListDTO;
import com.eUprava.eUprava.model.entity.OruzniList;
import com.eUprava.eUprava.payload.OruzniListPostRequest;
import com.eUprava.eUprava.repository.OruzniListRepository;
import com.eUprava.eUprava.service.OruzniListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/oruzniList")
@CrossOrigin(origins = "http://localhost:4200")

public class OruzniListController {

    private final OruzniListRepository oruzniListRepository;
    private final OruzniListService oruzniListService;

    OruzniListController(OruzniListRepository oruzniListRepository,OruzniListService oruzniListService) {
        this.oruzniListRepository = oruzniListRepository;
        this.oruzniListService = oruzniListService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<OruzniList> findOne(@PathVariable Long id) {
        OruzniList oruzniList = (OruzniList) oruzniListRepository.findById(id)
                .orElseThrow(() -> new OruzniListNotFoundExeption("OruzniList sa datim id ne postoji"));

        return new ResponseEntity<>(oruzniList, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<OruzniList>> findAll() {
        List<OruzniList> oruzniList = oruzniListService.findAll();
        return new ResponseEntity<>(oruzniList, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<OruzniList> save(@Valid @RequestBody OruzniListDTO oruzniListDTO) {
        OruzniList oruzniList = oruzniListService.save(oruzniListDTO);
        return new ResponseEntity<>(oruzniList, HttpStatus.CREATED);

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<OruzniList> update(@PathVariable("id") Long id,
                                          @Valid @RequestBody OruzniListPostRequest oruzniListPostRequest) {
        OruzniList update = oruzniListService.update(id, oruzniListPostRequest);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteOruzniList(@PathVariable Long id) {

        oruzniListRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
