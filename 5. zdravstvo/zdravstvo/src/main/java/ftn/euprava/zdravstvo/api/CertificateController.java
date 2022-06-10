package ftn.euprava.zdravstvo.api;

import ftn.euprava.zdravstvo.api.dto.CertificateRequest;
import ftn.euprava.zdravstvo.api.dto.CertificateResponse;
import ftn.euprava.zdravstvo.api.dto.CheckUserCertificateResponse;
import ftn.euprava.zdravstvo.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@Validated
@RequestMapping("/api/medical-certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @PostMapping
    public ResponseEntity<CertificateResponse> generateCertificateForUser(
            @RequestBody CertificateRequest request
    ){
        return ResponseEntity.ok(certificateService.generateCertificate(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CheckUserCertificateResponse> checkIfUserHasCertificate(@PathVariable Long userId) {
        return ResponseEntity.ok(certificateService.checkIfUserHasCertificateNoted(userId));
    }
}
