package ftn.euprava.zdravstvo.service;

import ftn.euprava.zdravstvo.api.dto.CertificateRequest;
import ftn.euprava.zdravstvo.api.dto.CertificateResponse;
import ftn.euprava.zdravstvo.api.dto.CheckUserCertificateResponse;
import ftn.euprava.zdravstvo.model.Certificate;
import ftn.euprava.zdravstvo.model.User;
import ftn.euprava.zdravstvo.repository.CertificateRepository;
import ftn.euprava.zdravstvo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    public CertificateResponse generateCertificate(final CertificateRequest request) {

        CertificateResponse response =  new CertificateResponse("Izdaje se lekarsko uverenje za "
                + request.getName() + " " + request.getLastName()
                + ", JMBG: " + request.getJmbg() + " u svrhu " + request.getPurpose());

        noteUserRequestedCertificate(request, response.getMessage());

        return response;
    }

    private void noteUserRequestedCertificate(final CertificateRequest request, final String message) {
        Certificate certificate = new Certificate(request.getJmbg(), message);
        certificateRepository.save(certificate);
    }

    public CheckUserCertificateResponse checkIfUserHasCertificateNoted(final String jmbg) {
        List<Certificate> certificatesOfUser = certificateRepository.findByUserJmbg(jmbg);
        if(certificatesOfUser.isEmpty()) {
            return new CheckUserCertificateResponse(false);
        }
        return new CheckUserCertificateResponse(true);
    }
}
