package ftn.euprava.mupvozila.service;

import ftn.euprava.mupvozila.model.RegistrationCertificate;
import ftn.euprava.mupvozila.web.dto.RegistrationCertificateDTO;

import java.util.List;

public interface IRegistrationCertificateService {

    RegistrationCertificate findOne(Long id);

    List<RegistrationCertificate> findAll();

    RegistrationCertificateDTO save(RegistrationCertificateDTO registrationCertificate);

    void delete(Long id);
}
