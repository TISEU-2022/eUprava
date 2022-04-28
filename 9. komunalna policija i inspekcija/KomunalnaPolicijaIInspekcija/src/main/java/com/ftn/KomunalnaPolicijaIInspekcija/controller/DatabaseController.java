package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.model.*;
import com.ftn.KomunalnaPolicijaIInspekcija.repository.*;
import com.ftn.KomunalnaPolicijaIInspekcija.service.VrstaPredstavkeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/db")
@CrossOrigin
public class DatabaseController {

    @Autowired
    private VrstaPredstavkeRepository vrstaPredstavkeRepository;

    @Autowired
    private VrstaKomunalnogProblemaRepository vrstaKomunalnogProblemaRepository;

    @Autowired
    private PodnosilacRepository podnosilacRepository;

    @Autowired
    private SluzbenikRepository sluzbenikRepository;

    @Autowired
    private PredstavkaRepository predstavkaRepository;

    @Autowired
    private KomunalniProblemRepository komunalniProblemRepository;

    @Autowired
    private IzvestajRepository izvestajRepository;


    @GetMapping("/create")
    public ResponseEntity<String> createDatabase() {

        VrstaPredstavke vrstaPredstavke1 = new VrstaPredstavke();
        vrstaPredstavke1.setNaziv("Predstavka komunalnoj inspekciji");
        vrstaPredstavke1 = vrstaPredstavkeRepository.save(vrstaPredstavke1);

        VrstaPredstavke vrstaPredstavke2 = new VrstaPredstavke();
        vrstaPredstavke2.setNaziv("Predstavka saobraćajnoj inspekciji");
        vrstaPredstavke2 = vrstaPredstavkeRepository.save(vrstaPredstavke2);

        VrstaPredstavke vrstaPredstavke3 = new VrstaPredstavke();
        vrstaPredstavke3.setNaziv("Predstavka inspekciji za zaštitu životne sredine");
        vrstaPredstavke3 = vrstaPredstavkeRepository.save(vrstaPredstavke3);


        VrstaKomunalnogProblema vrstaKomunalnogProblema1 = new VrstaKomunalnogProblema();
        vrstaKomunalnogProblema1.setNaziv("Zagađenje (voda, vazduh, neopasan otpad)");
        vrstaKomunalnogProblema1 = vrstaKomunalnogProblemaRepository.save(vrstaKomunalnogProblema1);

        VrstaKomunalnogProblema vrstaKomunalnogProblema2 = new VrstaKomunalnogProblema();
        vrstaKomunalnogProblema2.setNaziv("Održavanje zelenih površina");
        vrstaKomunalnogProblema2 = vrstaKomunalnogProblemaRepository.save(vrstaKomunalnogProblema2);

        VrstaKomunalnogProblema vrstaKomunalnogProblema3 = new VrstaKomunalnogProblema();
        vrstaKomunalnogProblema3.setNaziv("Održavanje kanalizacije");
        vrstaKomunalnogProblema3 = vrstaKomunalnogProblemaRepository.save(vrstaKomunalnogProblema3);


        Sluzbenik sluzbenik1 = new Sluzbenik();
        sluzbenik1.setIme("Miša");
        sluzbenik1.setPrezime("Mišić");
        sluzbenik1.setJmbg("1605998654321");
        sluzbenik1.setEmail("misa@gmail.com");
        sluzbenik1 = sluzbenikRepository.save(sluzbenik1);

        Sluzbenik sluzbenik2 = new Sluzbenik();
        sluzbenik2.setIme("Ana");
        sluzbenik2.setPrezime("Anić");
        sluzbenik2.setJmbg("1510000987654");
        sluzbenik2.setEmail("ana@gmail.com");
        sluzbenik2 = sluzbenikRepository.save(sluzbenik2);

        Sluzbenik sluzbenik3 = new Sluzbenik();
        sluzbenik3.setIme("Mila");
        sluzbenik3.setPrezime("Milić");
        sluzbenik3.setJmbg("0404001695847");
        sluzbenik3.setEmail("mila@gmail.com");
        sluzbenik3 = sluzbenikRepository.save(sluzbenik3);


        Podnosilac podnosilac1 = new Podnosilac();
        podnosilac1.setIme("Pera");
        podnosilac1.setPrezime("Perić");
        podnosilac1.setAdresa("Bulevar Oslobođenja 12");
        podnosilac1.setMesto("Novi Sad");
        podnosilac1.setBrojTelefona("+3816459784563");
        podnosilac1.setEmail("pera@gmail.com");
        podnosilac1.setJmbg("1510987653214");
        podnosilac1.setPttBroj(21117);
        podnosilac1 = podnosilacRepository.save(podnosilac1);

        Podnosilac podnosilac2 = new Podnosilac();
        podnosilac2.setIme("Žika");
        podnosilac2.setPrezime("Žikić");
        podnosilac2.setAdresa("Kisačka 33");
        podnosilac2.setMesto("Novi Sad");
        podnosilac2.setBrojTelefona("+381698634161");
        podnosilac2.setEmail("zika@gmail.com");
        podnosilac2.setJmbg("0909991123123");
        podnosilac2.setPttBroj(21116);
        podnosilac2 = podnosilacRepository.save(podnosilac2);

        Podnosilac podnosilac3 = new Podnosilac();
        podnosilac3.setIme("Mika");
        podnosilac3.setPrezime("Mikić");
        podnosilac3.setAdresa("Bulevar Kralja Petra 29");
        podnosilac3.setMesto("Novi Sad");
        podnosilac3.setBrojTelefona("+3812412384556");
        podnosilac3.setEmail("mika@gmail.com");
        podnosilac3.setJmbg("1212000985234");
        podnosilac3.setPttBroj(21116);
        podnosilac3 = podnosilacRepository.save(podnosilac3);


        Izvestaj izvestaj1 = new Izvestaj();
        izvestaj1.setIzvestaj("Ovo je izvestaj 1. Ovo je izvestaj 1. Ovo je izvestaj 1. Ovo je izvestaj 1. Ovo je izvestaj 1. Ovo je izvestaj 1. Ovo je izvestaj 1.");
        izvestaj1.setPrihvaceno(true);
        izvestaj1.setVremePodnosenja(new Date());
        izvestaj1.setSluzbenik(sluzbenik1);
        izvestaj1 = izvestajRepository.save(izvestaj1);

        Izvestaj izvestaj2 = new Izvestaj();
        izvestaj2.setIzvestaj("Ovo je izvestaj 2. Ovo je izvestaj 2. Ovo je izvestaj 2. Ovo je izvestaj 2. Ovo je izvestaj 2. Ovo je izvestaj 2. Ovo je izvestaj 2.");
        izvestaj2.setPrihvaceno(true);
        izvestaj2.setVremePodnosenja(new Date());
        izvestaj2.setSluzbenik(sluzbenik2);
        izvestaj2 = izvestajRepository.save(izvestaj2);

        Izvestaj izvestaj3 = new Izvestaj();
        izvestaj3.setIzvestaj("Ovo je izvestaj 3. Ovo je izvestaj 3. Ovo je izvestaj 3. Ovo je izvestaj 3. Ovo je izvestaj 3. Ovo je izvestaj 3. Ovo je izvestaj 3.");
        izvestaj3.setPrihvaceno(true);
        izvestaj3.setVremePodnosenja(new Date());
        izvestaj3.setSluzbenik(sluzbenik3);
        izvestaj3 = izvestajRepository.save(izvestaj3);


        Predstavka predstavka1 = new Predstavka();
        predstavka1.setNaslov("Predstavka 1");
        predstavka1.setOpis("Opis predstavke 1");
        predstavka1.setVremePodnosenja(new Date());
        predstavka1.setDatumDogadjaja(new Date());
        predstavka1.setAdresaDogadjaja("Kisacka 32");
        predstavka1.setMestoDogadjaja("Novi Sad");
        predstavka1.setIzvestaj(izvestaj1);
        predstavka1.setVrstaPredstavke(vrstaPredstavke1);
        predstavka1 = predstavkaRepository.save(predstavka1);

        Predstavka predstavka2 = new Predstavka();
        predstavka2.setNaslov("Naslov predstavke 2");
        predstavka2.setOpis("Opis predstavke 2");
        predstavka2.setVremePodnosenja(new Date());
        predstavka2.setDatumDogadjaja(new Date());
        predstavka2.setAdresaDogadjaja("Vuka Karadžića 32");
        predstavka2.setMestoDogadjaja("Novi Sad");
        predstavka2.setIzvestaj(null);
        predstavka2.setVrstaPredstavke(vrstaPredstavke2);
        predstavka2 = predstavkaRepository.save(predstavka2);



        KomunalniProblem komunalniProblem1 = new KomunalniProblem();
        komunalniProblem1.setOpis("Opis komunalnog problema 1");
        komunalniProblem1.setPodnosilac(podnosilac1);
        komunalniProblem1.setAdresaDogadjaja("Miroslava Antica 13");
        komunalniProblem1.setMestoDogadjaja("Beograd");
        komunalniProblem1.setDatumDogadjaja(new Date());
        komunalniProblem1.setDatumPodnosenja(new Date());
        komunalniProblem1.setVrstaKomunalnogProblema(vrstaKomunalnogProblema1);
        komunalniProblem1.setIzvestaj(izvestaj2);
        komunalniProblem1 = komunalniProblemRepository.save(komunalniProblem1);

        KomunalniProblem komunalniProblem2 = new KomunalniProblem();
        komunalniProblem2.setOpis("Opis komunalnog problema 2");
        komunalniProblem2.setPodnosilac(podnosilac2);
        komunalniProblem2.setAdresaDogadjaja("Zdravka Colica 13");
        komunalniProblem2.setMestoDogadjaja("Sombor");
        komunalniProblem2.setDatumDogadjaja(new Date());
        komunalniProblem2.setDatumPodnosenja(new Date());
        komunalniProblem2.setVrstaKomunalnogProblema(vrstaKomunalnogProblema2);
        komunalniProblem2.setIzvestaj(null);
        komunalniProblem2 = komunalniProblemRepository.save(komunalniProblem2);

        KomunalniProblem komunalniProblem3 = new KomunalniProblem();
        komunalniProblem3.setOpis("Opis komunalnog problema 3");
        komunalniProblem3.setPodnosilac(podnosilac3);
        komunalniProblem3.setAdresaDogadjaja("Konstantina Antica 13");
        komunalniProblem3.setMestoDogadjaja("Užice");
        komunalniProblem3.setDatumDogadjaja(new Date());
        komunalniProblem3.setDatumPodnosenja(new Date());
        komunalniProblem3.setVrstaKomunalnogProblema(vrstaKomunalnogProblema3);
        komunalniProblem3.setIzvestaj(izvestaj3);
        komunalniProblem3 = komunalniProblemRepository.save(komunalniProblem3);


        return ResponseEntity
                .ok()
                .build();
    }
}
