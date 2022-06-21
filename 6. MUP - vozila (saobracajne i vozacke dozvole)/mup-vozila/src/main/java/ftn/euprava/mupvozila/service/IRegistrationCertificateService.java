package ftn.euprava.mupvozila.service;

import ftn.euprava.mupvozila.model.RegistrationCertificate;
import ftn.euprava.mupvozila.web.dto.RegistrationCertificateDTO;

import java.util.List;

public interface IRegistrationCertificateService {

    RegistrationCertificateDTO findOne(Long id);

    List<RegistrationCertificate> findAll();

    List<RegistrationCertificateDTO> getAllRequests();

    List<RegistrationCertificateDTO> findAllByUserId(String userId);

    RegistrationCertificateDTO getRequestForUser(String userId);

    RegistrationCertificateDTO createRequest(RegistrationCertificateDTO registrationCertificateDTO);

    RegistrationCertificateDTO createCertificationRequest(RegistrationCertificateDTO registrationCertificateDTO, Long requestId);

    RegistrationCertificateDTO save(RegistrationCertificateDTO registrationCertificate);

    void delete(Long id);

}
