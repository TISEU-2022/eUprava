package ftn.euprava.mupvozila.web.controller;

import ftn.euprava.mupvozila.service.IRegistrationCertificateService;
import ftn.euprava.mupvozila.web.dto.RegistrationCertificateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/registration-certificate")
public class RegistrationCertificateController {

    private final IRegistrationCertificateService iRegistrationCertificateService;

    public RegistrationCertificateController(IRegistrationCertificateService iRegistrationCertificateService) {
        this.iRegistrationCertificateService = iRegistrationCertificateService;
    }

    @PostMapping
    public ResponseEntity<RegistrationCertificateDTO> createRegistrationCertificate(@RequestBody RegistrationCertificateDTO registrationCertificateDTO){
        return new ResponseEntity<>(iRegistrationCertificateService.save(registrationCertificateDTO), HttpStatus.CREATED);
    }
}
