package ftn.euprava.mupvozila.web.controller;

import ftn.euprava.mupvozila.service.IRegistrationCertificateService;
import ftn.euprava.mupvozila.web.dto.RegistrationCertificateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/registration-certificate")
public class RegistrationCertificateController {

    private final IRegistrationCertificateService iRegistrationCertificateService;

    public RegistrationCertificateController(IRegistrationCertificateService iRegistrationCertificateService) {
        this.iRegistrationCertificateService = iRegistrationCertificateService;
    }

    @PostMapping(value = "/request")
    public ResponseEntity<RegistrationCertificateDTO> createRequestForRegistrationCertificate(@RequestBody RegistrationCertificateDTO registrationCertificateDTO){
        return new ResponseEntity<>(iRegistrationCertificateService.createRequest(registrationCertificateDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/request")
    public ResponseEntity<List<RegistrationCertificateDTO>> getAllRequestsForCreating(){
        return new ResponseEntity<>(iRegistrationCertificateService.getAllRequests(), HttpStatus.OK);
    }

    @GetMapping(value = "/request/{requestId}")
    public ResponseEntity<RegistrationCertificateDTO> getRequestStatus(@PathVariable Long requestId){
        return new ResponseEntity<>(iRegistrationCertificateService.findOne(requestId), HttpStatus.OK);
    }

    @GetMapping(value = "/request/user/{userId}")
    public ResponseEntity<RegistrationCertificateDTO> getRequestForUser(@PathVariable String userId){
        return new ResponseEntity<>(iRegistrationCertificateService.getRequestForUser(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/request")
    public ResponseEntity<RegistrationCertificateDTO> changeRequestStatus(@RequestBody RegistrationCertificateDTO registrationCertificateDTO){
        return new ResponseEntity<>(iRegistrationCertificateService.save(registrationCertificateDTO), HttpStatus.OK);
    }

    @PutMapping(value = "/{requestId}")
    public  ResponseEntity<RegistrationCertificateDTO> createRegistrationCertificate(@RequestBody RegistrationCertificateDTO registrationCertificateDTO,
                                                                                     @PathVariable Long requestId){
        return new ResponseEntity<>(iRegistrationCertificateService.createCertificationRequest(registrationCertificateDTO, requestId),
                                    HttpStatus.CREATED);
    }

}
