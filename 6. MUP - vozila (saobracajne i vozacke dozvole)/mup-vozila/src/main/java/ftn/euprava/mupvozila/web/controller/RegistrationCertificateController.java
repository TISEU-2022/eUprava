package ftn.euprava.mupvozila.web.controller;

import ftn.euprava.mupvozila.service.IRegistrationCertificateService;
import ftn.euprava.mupvozila.web.dto.RegistrationCertificateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/registration-certificates")
public class RegistrationCertificateController {

    private final IRegistrationCertificateService iRegistrationCertificateService;

    public RegistrationCertificateController(IRegistrationCertificateService iRegistrationCertificateService) {
        this.iRegistrationCertificateService = iRegistrationCertificateService;
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<RegistrationCertificateDTO>> getRegistrationCertificatesForUser(@PathVariable String userId){
        return new ResponseEntity<>(iRegistrationCertificateService.findAllByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/user/registration-certificate/{id}")
    public ResponseEntity<RegistrationCertificateDTO> getRegistrationCertificate(@PathVariable Long id){
        return new ResponseEntity<>(iRegistrationCertificateService.findOne(id),HttpStatus.OK);
    }

    @PostMapping(value = "/requests")
    public ResponseEntity<RegistrationCertificateDTO> createRequestForRegistrationCertificate(@RequestBody RegistrationCertificateDTO registrationCertificateDTO){
        return new ResponseEntity<>(iRegistrationCertificateService.createRequest(registrationCertificateDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/requests")
    public ResponseEntity<List<RegistrationCertificateDTO>> getAllRequestsForCreating(){
        return new ResponseEntity<>(iRegistrationCertificateService.getAllRequests(), HttpStatus.OK);
    }

    @GetMapping(value = "/requests/{requestId}")
    public ResponseEntity<RegistrationCertificateDTO> getRequestStatus(@PathVariable Long requestId){
        return new ResponseEntity<>(iRegistrationCertificateService.findOne(requestId), HttpStatus.OK);
    }

    @GetMapping(value = "/requests/user/{userId}")
    public ResponseEntity<RegistrationCertificateDTO> getRequestForUser(@PathVariable String userId){
        Logger.getAnonymousLogger().info("Request status za korisnika " + userId);

        return new ResponseEntity<>(iRegistrationCertificateService.getRequestForUser(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/requests")
    public ResponseEntity<RegistrationCertificateDTO> changeRequestStatus(@RequestBody RegistrationCertificateDTO registrationCertificateDTO){
        return new ResponseEntity<>(iRegistrationCertificateService.save(registrationCertificateDTO), HttpStatus.OK);
    }

    @PutMapping(value = "/{requestId}")
    public  ResponseEntity<RegistrationCertificateDTO> createRegistrationCertificate(@RequestBody RegistrationCertificateDTO registrationCertificateDTO,
                                                                                     @PathVariable Long requestId){
        return new ResponseEntity<>(iRegistrationCertificateService.createCertificationRequest(registrationCertificateDTO, requestId),
                                    HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/requests/{requestId}")
    public ResponseEntity<Void> declineRequest(@PathVariable Long requestId){

        iRegistrationCertificateService.delete(requestId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
