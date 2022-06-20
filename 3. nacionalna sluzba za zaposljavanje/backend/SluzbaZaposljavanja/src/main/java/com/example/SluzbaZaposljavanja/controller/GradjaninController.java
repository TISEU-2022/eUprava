package com.example.SluzbaZaposljavanja.controller;
import com.example.SluzbaZaposljavanja.model.Firma;
import com.example.SluzbaZaposljavanja.model.Gradjanin;
import com.example.SluzbaZaposljavanja.model.GradjaninPDFExporter;
import com.example.SluzbaZaposljavanja.model.OglasZaPosao;
import com.example.SluzbaZaposljavanja.service.GradjaninService;
import com.example.SluzbaZaposljavanja.service.OglasZaPosaoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping(value = "api/gradjani")
public class GradjaninController {

	private static final String MATICAR_API_URL = "http://maticar:4002/api/user/";
	
    @Autowired
    private GradjaninService gradjaninService;

    @GetMapping
    public ResponseEntity<List<Gradjanin>> getGradjane(){
        List<Gradjanin> gradjani = gradjaninService.findAll();
        return new ResponseEntity<>(gradjani, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Gradjanin> getSemesters(@PathVariable("id") Integer id){
        Gradjanin gradjanin = gradjaninService.findOne(id);
        if(gradjanin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gradjanin, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Gradjanin> save(@RequestBody Gradjanin gradjanin){
        Gradjanin newGradjanin = gradjaninService.save(gradjanin);
        return ResponseEntity.status(201).body(newGradjanin);
    }
    
    

    @GetMapping(value = "zaposlenje/{korisnickoIme}")
    public ResponseEntity<Boolean> getStatusZaposlenjaGradjanina(@PathVariable("korisnickoIme") String korisnickoIme){
        Gradjanin gradjanin = gradjaninService.findByKorisnickoIme(korisnickoIme);
        Firma firma = gradjanin.getFirma();
        boolean zaposlenje;
        if(firma == null) {
            zaposlenje = false;
        }else{
            zaposlenje = true;
        }
        if(gradjanin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Boolean>(zaposlenje, HttpStatus.OK);
    }

    @GetMapping(value = "username/{korisnickoIme}")
    public ResponseEntity<Gradjanin> getGradjaninByKorisnickoIme(@PathVariable("korisnickoIme") String korisnickoIme){
        Gradjanin gradjanin = gradjaninService.findByKorisnickoIme(korisnickoIme);

        return new ResponseEntity<Gradjanin>(gradjanin, HttpStatus.OK);
    }

    @GetMapping("/export/{username}")
    public void exportToPDFGradjanina(HttpServletResponse response, @PathVariable String username) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());


        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=gradjanin-" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        List<Gradjanin> listaGradjana = gradjaninService.findAll();

        Gradjanin gradjanin = gradjaninService.findByKorisnickoIme(username);


        GradjaninPDFExporter exporter = new GradjaninPDFExporter(gradjanin);
        exporter.export(response);
    }
    
    @GetMapping("/info/{jmbg}")
    public ResponseEntity<?> getUserInfo(@PathVariable("jmbg") String jmbg) {
    	final RestTemplate restTemplate = new RestTemplate();
    	try {
        	ResponseEntity<JsonNode> response = restTemplate.getForEntity(MATICAR_API_URL  + jmbg, JsonNode.class);
        	return response;
        }
        catch (Exception e) {
        	return null;
        }
    }

}
