package ftn.euprava.zdravstvo.service;

import ftn.euprava.zdravstvo.api.dto.CertificateRequest;
import ftn.euprava.zdravstvo.api.dto.CertificateResponse;
import org.springframework.stereotype.Service;

@Service
public class CertificateService {

    public CertificateResponse generateCertificate(CertificateRequest request) {
        return new CertificateResponse("Izdaje se lekarsko uverenje za "
                + request.getName() + " " + request.getLastName()
        + ", JMBG: " + request.getJmbg() + " u svrhu " + request.getPurpose());
    }
}
