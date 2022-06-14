package ftn.euprava.mupvozila.service.implementation;

import ftn.euprava.mupvozila.model.Car;
import ftn.euprava.mupvozila.model.RegistrationCertificate;
import ftn.euprava.mupvozila.repository.RegistrationCertificateRepository;
import ftn.euprava.mupvozila.service.ICarService;
import ftn.euprava.mupvozila.service.IRegistrationCertificateService;
import ftn.euprava.mupvozila.util.exception.RequestIsNotValidException;
import ftn.euprava.mupvozila.util.exception.EntityNotFoundException;
import ftn.euprava.mupvozila.util.mapper.CarMapper;
import ftn.euprava.mupvozila.util.mapper.RegistrationCertificateMapper;
import ftn.euprava.mupvozila.web.dto.CarDTO;
import ftn.euprava.mupvozila.web.dto.RegistrationCertificateDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class RegistrationCertificateService implements IRegistrationCertificateService {

    private final RegistrationCertificateRepository registrationCertificateRepository;
    private final RegistrationCertificateMapper registrationCertificateMapper;
    private final ICarService iCarService;
    private final CarMapper carMapper;

    public RegistrationCertificateService(RegistrationCertificateRepository registrationCertificateRepository, RegistrationCertificateMapper registrationCertificateMapper, ICarService iCarService, CarMapper carMapper) {
        this.registrationCertificateRepository = registrationCertificateRepository;
        this.registrationCertificateMapper = registrationCertificateMapper;
        this.iCarService = iCarService;
        this.carMapper = carMapper;
    }

    @Override
    public RegistrationCertificateDTO findOne(Long id) {
        RegistrationCertificate registrationCertificate = registrationCertificateRepository.findById(id).orElse(null);
        if (registrationCertificate == null) {
            throw new EntityNotFoundException("Registration certificate not found");
        }
        return registrationCertificateMapper.toDto(registrationCertificate);
    }

    @Override
    public List<RegistrationCertificate> findAll() {
        return registrationCertificateRepository.findAll();
    }

    @Override
    public List<RegistrationCertificateDTO> getAllRequests() {
        return registrationCertificateRepository.findAllByRequestTrue().stream().map(registrationCertificateMapper::toDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public RegistrationCertificateDTO getRequestForUser(String userId) {

        RegistrationCertificate registrationCertificate = registrationCertificateRepository.findByUserId(userId).orElse(null);
        if (registrationCertificate == null)
            throw new EntityNotFoundException("Zahtev nije pronadjen.");

        Logger.getAnonymousLogger().info("Request za korisnika " + registrationCertificate);

        return registrationCertificateMapper.toDto(registrationCertificate);
    }

    @Override
    public RegistrationCertificateDTO save(RegistrationCertificateDTO registrationCertificate) {
        RegistrationCertificate certificate = registrationCertificateRepository.save(registrationCertificateMapper.toEntity(registrationCertificate));
        return registrationCertificateMapper.toDto(certificate);
    }

    @Override
    public RegistrationCertificateDTO createRequest(RegistrationCertificateDTO registrationCertificateDTO) {

        RegistrationCertificate certificate = new RegistrationCertificate();
        certificate.setUserId(registrationCertificateDTO.getUserId());
        Car car = carMapper.toEntity(iCarService.save(registrationCertificateDTO.getCarDTO()));
        certificate.setCar(car);
        certificate.setRequest(true);

        certificate = registrationCertificateRepository.save(certificate);

        return registrationCertificateMapper.toDto(certificate);
    }

    @Override
    public RegistrationCertificateDTO createCertificationRequest(RegistrationCertificateDTO registrationCertificateDTO, Long requestId) {

        RegistrationCertificate certificate = registrationCertificateRepository.findById(requestId).orElse(null);

        if (certificate == null)
            throw new EntityNotFoundException("Zahtev nije pronadjen");

        if (certificate.getRequest())
            throw new RequestIsNotValidException("Zahtev nije validan");

        certificate.setDayOfIssue(LocalDate.now());
        certificate.setRequest(false);
        certificate.setStatus(true);
        certificate.setPlaceOfIssue(registrationCertificateDTO.getPlaceOfIssue());
        certificate.setLicensePlate(registrationCertificateDTO.getLicencePlate());

        certificate = registrationCertificateRepository.save(certificate);

        return registrationCertificateMapper.toDto(certificate);
    }

    @Override
    public void delete(Long id) {
        registrationCertificateRepository.deleteById(id);
    }

}
