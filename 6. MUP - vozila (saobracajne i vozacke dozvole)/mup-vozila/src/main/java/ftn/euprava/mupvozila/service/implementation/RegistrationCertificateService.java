package ftn.euprava.mupvozila.service.implementation;

import ftn.euprava.mupvozila.model.RegistrationCertificate;
import ftn.euprava.mupvozila.repository.RegistrationCertificateRepository;
import ftn.euprava.mupvozila.service.IRegistrationCertificateService;
import ftn.euprava.mupvozila.util.mapper.RegistrationCertificateMapper;
import ftn.euprava.mupvozila.web.dto.RegistrationCertificateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationCertificateService implements IRegistrationCertificateService {

    private final RegistrationCertificateRepository registrationCertificateRepository;
    private final RegistrationCertificateMapper registrationCertificateMapper;

    public RegistrationCertificateService(RegistrationCertificateRepository registrationCertificateRepository, RegistrationCertificateMapper registrationCertificateMapper) {
        this.registrationCertificateRepository = registrationCertificateRepository;
        this.registrationCertificateMapper = registrationCertificateMapper;
    }

    @Override
    public RegistrationCertificate findOne(Long id) {
        return registrationCertificateRepository.findById(id).orElse(null);
    }

    @Override
    public List<RegistrationCertificate> findAll() {
        return registrationCertificateRepository.findAll();
    }

    @Override
    public RegistrationCertificateDTO save(RegistrationCertificateDTO registrationCertificate) {
        RegistrationCertificate certificate = registrationCertificateRepository.save(registrationCertificateMapper.toEntity(registrationCertificate));
        return registrationCertificateMapper.toDto(certificate);
    }

    @Override
    public void delete(Long id) {
        registrationCertificateRepository.deleteById(id);
    }
}
