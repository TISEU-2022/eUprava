package ftn.euprava.mupvozila.web.controller;

import ftn.euprava.mupvozila.service.IDrivingLicenceService;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/driving-licence")
public class DrivingLicenceController {

    private final IDrivingLicenceService iDrivingLicenceService;

    public DrivingLicenceController(IDrivingLicenceService iDrivingLicenceService) {
        this.iDrivingLicenceService = iDrivingLicenceService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DrivingLicenceDTO> getDrivingLicenceById(@PathVariable Long id){
        return new ResponseEntity<>(iDrivingLicenceService.findOne(id), HttpStatus.OK);
    }

//    @GetMapping(value = "/user/{identityNumber}")
//    public ResponseEntity<DrivingLicenceDTO> getDrivingLicenceByIdentityNumber(@PathVariable String identityNumber){
//        return new ResponseEntity<>(iDrivingLicenceService.findOneByIdentityNumber(identityNumber), HttpStatus.OK);
//    }
}
