package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.*;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.IzvestajMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.KomunalniProblemMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.SluzbenikMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Izvestaj;
import com.ftn.KomunalnaPolicijaIInspekcija.model.KomunalniProblem;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Sluzbenik;
import com.ftn.KomunalnaPolicijaIInspekcija.service.IzvestajService;
import com.ftn.KomunalnaPolicijaIInspekcija.service.KomunalniProblemService;
import com.ftn.KomunalnaPolicijaIInspekcija.service.PodnosilacService;
import com.ftn.KomunalnaPolicijaIInspekcija.service.SluzbenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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

        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        List<KomunalniProblem> komunalniProblemi = new ArrayList<>();
        boolean isSluzbenik = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_SLUZBENIK"));
        if(isSluzbenik) {
            komunalniProblemi = komunalniProblemService.getAll();
        } else {
            String jmbg = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            Podnosilac podnosilac = podnosilacService.getOneByJmbg(jmbg);
            if(podnosilac != null) {
                komunalniProblemi = komunalniProblemService.getByPodnosilac(podnosilac);
            }
        }

        List<KomunalniProblemDTO> dtos = new ArrayList<>();
        for(KomunalniProblem kp : komunalniProblemi){
            dtos.add(KomunalniProblemMapper.mapDTO(kp));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KomunalniProblemDTO> getOne(@PathVariable Long id){
        KomunalniProblem komunalniProblem = komunalniProblemService.getOne(id);

        return new ResponseEntity<>(KomunalniProblemMapper.mapDTO(komunalniProblem), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_PODNOSILAC')")
    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<?> createKomunalniProblem(@ModelAttribute KomunalniProblemRequestDTO komunalniProblemRequestDTO){
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object> maticarResponse = restTemplate.
                    getForEntity("http://maticar-app:4002/api/user/" + komunalniProblemRequestDTO.getJmbg().toString(), Object.class );
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (HttpClientErrorException.MethodNotAllowed httpClientErrorExceptionMethodNotAllowed){
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PreAuthorize("hasRole('ROLE_SLUZBENIK')")
    @PostMapping(value="/izvestaj/{id}")
    public ResponseEntity<?> writeIzvestaj(@PathVariable("id") Long id, @RequestBody IzvestajRequestDTO izvestajDTO){
        System.out.println(izvestajDTO);
        KomunalniProblem komunalniProblem = komunalniProblemService.getOne(id);
        String jmbg = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Sluzbenik sluzbenik = SluzbenikMapper.mapModel(sluzbenikService.getByJmbg(jmbg));
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

    @PreAuthorize("hasRole('ROLE_SLUZBENIK')")
    @PostMapping(value="/odbaci-izvestaj/{id}")
    public ResponseEntity<?> rejectIzvestaj(@PathVariable("id") Long id){
        KomunalniProblem komunalniProblem = komunalniProblemService.getOne(id);
        String jmbg = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Sluzbenik sluzbenik = SluzbenikMapper.mapModel(sluzbenikService.getByJmbg(jmbg));
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
