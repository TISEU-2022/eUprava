package ftn.euprava.zdravstvo.api;

import ftn.euprava.zdravstvo.api.dto.CertificateRequest;
import ftn.euprava.zdravstvo.api.dto.CertificateResponse;
import ftn.euprava.zdravstvo.api.dto.CheckUserCertificateResponse;
import ftn.euprava.zdravstvo.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin
@RestController
@Validated
@RequestMapping("/api/medical-certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @PostMapping
    public ResponseEntity<CertificateResponse> generateCertificateForUser(
            @RequestBody @Valid CertificateRequest request
    ){
        return ResponseEntity.ok(certificateService.generateCertificate(request));
    }

    @GetMapping("/{jmbg}")
    public ResponseEntity<CheckUserCertificateResponse> checkIfUserHasCertificate(@PathVariable String jmbg) {
        return ResponseEntity.ok(certificateService.checkIfUserHasCertificateNoted(jmbg));
    }
}
