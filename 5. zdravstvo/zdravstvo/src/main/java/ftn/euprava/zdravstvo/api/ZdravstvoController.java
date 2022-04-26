package ftn.euprava.zdravstvo.api;

import ftn.euprava.zdravstvo.api.dto.UverenjeDTO;
import ftn.euprava.zdravstvo.api.dto.UverenjeZahtevDTO;
import ftn.euprava.zdravstvo.model.Termin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller("/api")
@CrossOrigin("*")
public class ZdravstvoController {




    @GetMapping("/medical-certificate")
    public UverenjeDTO getLekarskoUverenje(@RequestBody()UverenjeZahtevDTO uverenjeZahtevDTO){
        UverenjeDTO uverenjeDTO = new UverenjeDTO();
        return uverenjeDTO;
    }

    @GetMapping("/appointment-history")
    public List<Termin> getHistory(){
        return null;
    }
}
