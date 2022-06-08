package com.ftn.glasanjebackend.kontroleri;

import com.ftn.glasanjebackend.model.Izbori;
import com.ftn.glasanjebackend.model.dto.IzboriDTO;
import com.ftn.glasanjebackend.service.IzboriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/izbori")
@CrossOrigin
public class IzboriKontroler {

    @Autowired
    private IzboriService izboriService;


    @GetMapping(value = "/izbori")
    public ResponseEntity<List<IzboriDTO>> getAllIzbori(){
        List<Izbori> izbori = izboriService.findAll();

        List<IzboriDTO> izboriDTO = new ArrayList<>();
        for(Izbori i : izbori){
            izboriDTO.add(new IzboriDTO(i));
        }
        return new ResponseEntity<>(izboriDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<IzboriDTO> getIzbor(@PathVariable Long id) {
        Izbori izbori = izboriService.findOne(id);
        if(izbori == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new IzboriDTO(izbori),HttpStatus.OK);
    }

}
