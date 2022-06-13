package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.model.*;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.IzvestajRequestDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.PredstavkaRequestDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.PredstavkaResponseDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.PredstavkaMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.SluzbenikMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.service.IzvestajService;
import com.ftn.KomunalnaPolicijaIInspekcija.service.PodnosilacService;
import com.ftn.KomunalnaPolicijaIInspekcija.service.PredstavkaService;
import com.ftn.KomunalnaPolicijaIInspekcija.service.SluzbenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/predstavka")
public class PredstavkaController {

    @Autowired
    private PredstavkaService predstavkaService;

    @Autowired
    private PodnosilacService podnosilacService;

    @Autowired
    private SluzbenikService sluzbenikService;

    @Autowired
    private IzvestajService izvestajService;

    @GetMapping
    public ResponseEntity<List<PredstavkaResponseDTO>> getAll(){

        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        List<PredstavkaResponseDTO> predstavkaResponseDTOs = new ArrayList<>();
        boolean isSluzbenik = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_SLUZBENIK"));
        if(isSluzbenik) {
            predstavkaResponseDTOs = predstavkaService.findAll();
        } else {
            String jmbg = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            Podnosilac podnosilac = podnosilacService.getOneByJmbg(jmbg);
            if(podnosilac != null) {
                predstavkaResponseDTOs = predstavkaService.findByPodnosilac(podnosilac);
            }
        }

        return new ResponseEntity<>(predstavkaResponseDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PredstavkaResponseDTO> findOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(PredstavkaMapper.mapDTO(predstavkaService.findOne(id)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_PODNOSILAC')")
    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<?> createPredstavka(@ModelAttribute PredstavkaRequestDTO predstavkaRequestDTO){
        try{
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object> maticarResponse = restTemplate.getForEntity("http://maticar-app:4002/api/user/" + predstavkaRequestDTO.getJmbg(), Object.class );
            System.out.println(maticarResponse);
            Predstavka predstavka = PredstavkaMapper.mapModel(predstavkaRequestDTO);
            predstavka.setVremePodnosenja(new Date());

            podnosilacService.create(predstavka.getPodnosilac());
            predstavka = predstavkaService.save(predstavka);

            return new ResponseEntity<>(predstavka.getId(), HttpStatus.CREATED);
        }catch (HttpClientErrorException.NotFound httpClientErrorExceptionNotFound){
            System.out.println("Ivke greska 404.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (HttpClientErrorException.MethodNotAllowed httpClientErrorExceptionMethodNotAllowed){
            System.out.println("Ivke greska 405.");
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @PreAuthorize("hasRole('ROLE_SLUZBENIK')")
    @PostMapping(value="/izvestaj/{id}")
    public ResponseEntity<?> writeIzvestaj(@PathVariable("id") Long id, @RequestBody IzvestajRequestDTO izvestajDTO){
        System.out.println(izvestajDTO);
        Predstavka predstavka = predstavkaService.findOne(id);
        Sluzbenik sluzbenik = SluzbenikMapper.mapModel(sluzbenikService.getOne(1L));
        Izvestaj novIzvestaj = new Izvestaj();
        novIzvestaj.setIzvestaj(izvestajDTO.getReport());
        novIzvestaj.setPrihvaceno(true);
        novIzvestaj.setVremePodnosenja(new Date());
        novIzvestaj.setSluzbenik(sluzbenik);
        Izvestaj vracen = izvestajService.save(novIzvestaj);
        predstavka.setIzvestaj(vracen);
        predstavkaService.save(predstavka);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_SLUZBENIK')")
    @PostMapping(value="/odbaci-izvestaj/{id}")
    public ResponseEntity<?> rejectIzvestaj(@PathVariable("id") Long id){
        Predstavka predstavka = predstavkaService.findOne(id);
        Sluzbenik sluzbenik = SluzbenikMapper.mapModel(sluzbenikService.getOne(1L));
        Izvestaj novIzvestaj = new Izvestaj();
        novIzvestaj.setIzvestaj("Vaša predstavka je odbijena.");
        novIzvestaj.setPrihvaceno(false);
        novIzvestaj.setVremePodnosenja(new Date());
        novIzvestaj.setSluzbenik(sluzbenik);
        Izvestaj vracen = izvestajService.save(novIzvestaj);
        predstavka.setIzvestaj(vracen);
        predstavkaService.save(predstavka);
        return ResponseEntity.ok().build();
    }
}
