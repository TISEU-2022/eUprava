package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.IzvestajDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.IzvestajRequestDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.KomunalniProblemDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.KomunalniProblemRequestDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.IzvestajMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.KomunalniProblemMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.SluzbenikMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Izvestaj;
import com.ftn.KomunalnaPolicijaIInspekcija.model.KomunalniProblem;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Sluzbenik;
import com.ftn.KomunalnaPolicijaIInspekcija.service.IzvestajService;
import com.ftn.KomunalnaPolicijaIInspekcija.service.KomunalniProblemService;
import com.ftn.KomunalnaPolicijaIInspekcija.service.PodnosilacService;
import com.ftn.KomunalnaPolicijaIInspekcija.service.SluzbenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/komunalni-problem")
public class KomunalniProblemController {

    @Autowired
    private KomunalniProblemService komunalniProblemService;

    @Autowired
    private PodnosilacService podnosilacService;

    @Autowired
    private SluzbenikService sluzbenikService;

    @Autowired
    private IzvestajService izvestajService;

    @GetMapping
    public ResponseEntity<List<KomunalniProblemDTO>> getAll(){
        List<KomunalniProblem> komunalniProblemi = komunalniProblemService.getAll();
        List<KomunalniProblemDTO> dtos = new ArrayList<>();
        for(KomunalniProblem kp : komunalniProblemi){
            dtos.add(KomunalniProblemMapper.mapDTO(kp));
        }
        System.out.println(dtos);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KomunalniProblemDTO> getOne(@PathVariable Long id){
        KomunalniProblem komunalniProblem = komunalniProblemService.getOne(id);

        return new ResponseEntity<>(KomunalniProblemMapper.mapDTO(komunalniProblem), HttpStatus.OK);
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<?> createKomunalniProblem(@ModelAttribute KomunalniProblemRequestDTO komunalniProblemRequestDTO){
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object> maticarResponse = restTemplate.getForEntity("http://maticar-app:4002/api/user/" + komunalniProblemRequestDTO.getJmbg().toString(), Object.class );
            KomunalniProblem komunalniProblem = KomunalniProblemMapper.mapModel(komunalniProblemRequestDTO);
            komunalniProblem.setDatumPodnosenja(new Date());

            podnosilacService.create(komunalniProblem.getPodnosilac());
            Long id = komunalniProblemService.createKomunalniProblem(komunalniProblem);

            String location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUriString();

            return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, location).body(id);

        }catch (HttpClientErrorException.NotFound httpClientErrorExceptionNotFound){
            System.out.println("Ivke greska 404.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (HttpClientErrorException.MethodNotAllowed httpClientErrorExceptionMethodNotAllowed){
            System.out.println("Ivke greska 405.");
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
}

    @PostMapping(value="/izvestaj/{id}")
    public ResponseEntity<?> writeIzvestaj(@PathVariable("id") Long id, @RequestBody IzvestajRequestDTO izvestajDTO){
        System.out.println(izvestajDTO);
        KomunalniProblem komunalniProblem = komunalniProblemService.getOne(id);
        Sluzbenik sluzbenik = SluzbenikMapper.mapModel(sluzbenikService.getOne(1L));
        Izvestaj novIzvestaj = new Izvestaj();
        novIzvestaj.setIzvestaj(izvestajDTO.getReport());
        novIzvestaj.setPrihvaceno(true);
        novIzvestaj.setVremePodnosenja(new Date());
        novIzvestaj.setSluzbenik(sluzbenik);
        Izvestaj vracen = izvestajService.save(novIzvestaj);
        komunalniProblem.setIzvestaj(vracen);
        komunalniProblemService.createKomunalniProblem(komunalniProblem);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="/odbaci-izvestaj/{id}")
    public ResponseEntity<?> rejectIzvestaj(@PathVariable("id") Long id){
        KomunalniProblem komunalniProblem = komunalniProblemService.getOne(id);
        Sluzbenik sluzbenik = SluzbenikMapper.mapModel(sluzbenikService.getOne(1L));
        Izvestaj novIzvestaj = new Izvestaj();
        novIzvestaj.setIzvestaj("Vaš izveštaj je odbijen.");
        novIzvestaj.setPrihvaceno(false);
        novIzvestaj.setVremePodnosenja(new Date());
        novIzvestaj.setSluzbenik(sluzbenik);
        Izvestaj vracen = izvestajService.save(novIzvestaj);
        komunalniProblem.setIzvestaj(vracen);
        komunalniProblemService.createKomunalniProblem(komunalniProblem);
        return ResponseEntity.ok().build();
    }

}
