package ftn.euprava.mupvozila.service.implementation;

import ftn.euprava.mupvozila.model.DrivingLicence;
import ftn.euprava.mupvozila.model.enums.DrivingLicenceType;
import ftn.euprava.mupvozila.model.enums.RequestType;
import ftn.euprava.mupvozila.repository.DrivingLicenceRepository;
import ftn.euprava.mupvozila.service.IDrivingLicenceService;
import ftn.euprava.mupvozila.util.mapper.DrivingLicenceMapper;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceChangeRequestDTO;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceDTO;
import ftn.euprava.mupvozila.web.dto.RequestForDrivingLicenceDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DrivingLicenceService implements IDrivingLicenceService {

    private final DrivingLicenceRepository drivingLicenceRepository;
    private final DrivingLicenceMapper drivingLicenceMapper;
    private final RequestForDrivingLicenceService requestForDrivingLicenceService;
    private final int VALIDITY_TIME = 10;

    public DrivingLicenceService(DrivingLicenceRepository drivingLicenceRepository, DrivingLicenceMapper drivingLicenceMapper,
                                 RequestForDrivingLicenceService requestForDrivingLicenceService) {
        this.drivingLicenceRepository = drivingLicenceRepository;
        this.drivingLicenceMapper = drivingLicenceMapper;
        this.requestForDrivingLicenceService = requestForDrivingLicenceService;
    }


    @Override
    public DrivingLicenceDTO findOne(Long id) {
        DrivingLicence drivingLicence = drivingLicenceRepository.findById(id).orElse(null);

        //TODO custom exception
        if(drivingLicence == null)
            throw new RuntimeException();

        return drivingLicenceMapper.toDto(drivingLicence);
    }

    @Override
    public DrivingLicenceDTO findOneByUserId(String userId) {
        DrivingLicence drivingLicence = drivingLicenceRepository.findByUserId(userId);

        if(drivingLicence == null)
            return null;

        return drivingLicenceMapper.toDto(drivingLicence);
    }

    @Override
    public DrivingLicenceDTO findOneByIdentityNumber(String identityNumber) {

        //TODO prosledis identity number do drugog servera i uzmes userov id i onda nadjes

//        DrivingLicence drivingLicence = drivingLicenceRepository.findByUser_IdentityNumber(identityNumber).orElse(null);
//
//        //TODO custom exception
//        if(drivingLicence == null)
//            throw new RuntimeException();
//
//        return drivingLicenceMapper.toDto(drivingLicence);
        return null;
    }

    @Override
    public List<DrivingLicence> findAll() {
        return drivingLicenceRepository.findAll();
    }

    @Override
    public DrivingLicenceDTO save(RequestForDrivingLicenceDTO requestForDrivingLicenceDTO) {
        DrivingLicenceDTO drivingLicenceDTO = requestForDrivingLicenceDTO.getDrivingLicenceDTO();
        drivingLicenceDTO.setDayOfIssue(LocalDate.now());
        drivingLicenceDTO.setValidUntil(LocalDate.now().plusYears(VALIDITY_TIME));
        drivingLicenceDTO.setDrivingLicenceType(requestForDrivingLicenceDTO.getDrivingLicenceType());
        drivingLicenceDTO.setUserId(requestForDrivingLicenceDTO.getCitizenId());

        // updating drivingLicenceDTO variable so that it has returned id and then adding it to
        // requestForLicenceService and then saving requestForLicenceService in order
        // to create their relationship
        drivingLicenceDTO = drivingLicenceMapper.toDto(drivingLicenceRepository.save(drivingLicenceMapper.toEntity(drivingLicenceDTO)));
        requestForDrivingLicenceDTO.setDrivingLicenceDTO(drivingLicenceDTO);
        requestForDrivingLicenceService.save(requestForDrivingLicenceDTO);
        return drivingLicenceDTO;
    }

    @Override
    public DrivingLicenceDTO update(DrivingLicenceChangeRequestDTO drivingLicenceChangeRequestDTO) {
        DrivingLicenceDTO drivingLicenceDTO = drivingLicenceChangeRequestDTO.getDrivingLicenceDTO();
        if (drivingLicenceChangeRequestDTO.getRequestType() == RequestType.EXPIRED){
            // updating card's expiration date,
            drivingLicenceDTO.setValidUntil(LocalDate.now().plusYears(VALIDITY_TIME));
        }

        else if (drivingLicenceChangeRequestDTO.getRequestType() == RequestType.LOST){
            // making a whole new driver licence, it's not going to override the old one
            drivingLicenceDTO.setDayOfIssue(LocalDate.now());
            drivingLicenceDTO.setValidUntil(LocalDate.now().plusYears(VALIDITY_TIME));
            drivingLicenceDTO.setId(null);
            drivingLicenceDTO.setDrivingLicenceType(DrivingLicenceType.DUPLICATE);
        }

        else if (drivingLicenceChangeRequestDTO.getRequestType() == RequestType.CHANGE_OF_INFORMATION){
            // don't have to do anything cuz all new info are already in drivingLicenceDTO
        }

        return drivingLicenceMapper.toDto(drivingLicenceRepository.save(drivingLicenceMapper.toEntity(drivingLicenceDTO)));
    }

    @Override
    public void delete(Long id) {
        drivingLicenceRepository.deleteById(id);
    }
}
