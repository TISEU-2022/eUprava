package ftn.euprava.zdravstvo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/zdravstvo")
@CrossOrigin("*")
public class ZdravstvoController {

    @GetMapping("/appointment-history")
    public ResponseEntity getHistory(){
        return new ResponseEntity(HttpStatus.OK);
    }
}
