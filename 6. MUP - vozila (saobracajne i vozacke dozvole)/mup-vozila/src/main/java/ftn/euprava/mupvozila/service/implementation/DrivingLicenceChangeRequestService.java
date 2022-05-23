package ftn.euprava.mupvozila.service.implementation;

import ftn.euprava.mupvozila.model.DrivingLicenceChangeRequest;
import ftn.euprava.mupvozila.model.RequestForDrivingLicence;
import ftn.euprava.mupvozila.model.enums.RequestStatus;
import ftn.euprava.mupvozila.repository.DrivingLicenceChangeRequestRepository;
import ftn.euprava.mupvozila.service.IDrivingLicenceChangeRequestService;
import ftn.euprava.mupvozila.util.jwt.JwtTokenUtil;
import ftn.euprava.mupvozila.util.mapper.DrivingLicenceChangeRequestMapper;
import ftn.euprava.mupvozila.util.mapper.DrivingLicenceMapper;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceChangeRequestDTO;
import ftn.euprava.mupvozila.web.dto.RequestForDrivingLicenceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrivingLicenceChangeRequestService implements IDrivingLicenceChangeRequestService {

    private final DrivingLicenceChangeRequestRepository drivingLicenceChangeRequestRepository;
    private final DrivingLicenceMapper drivingLicenceMapper;
    private final DrivingLicenceChangeRequestMapper drivingLicenceChangeRequestMapper;
    private final JwtTokenUtil jwtTokenUtil;

    public DrivingLicenceChangeRequestService(DrivingLicenceChangeRequestRepository drivingLicenceChangeRequestRepository,
                                              DrivingLicenceMapper drivingLicenceMapper, DrivingLicenceChangeRequestMapper drivingLicenceChangeRequestMapper, JwtTokenUtil jwtTokenUtil) {
        this.drivingLicenceChangeRequestRepository = drivingLicenceChangeRequestRepository;
        this.drivingLicenceMapper = drivingLicenceMapper;
        this.drivingLicenceChangeRequestMapper = drivingLicenceChangeRequestMapper;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    public DrivingLicenceChangeRequest findOne(Long id) {
        return drivingLicenceChangeRequestRepository.findById(id).orElse(null);
    }

    @Override
    public DrivingLicenceChangeRequestDTO findPendingRequest(String userId) {
        List<DrivingLicenceChangeRequest> requests = drivingLicenceChangeRequestRepository.findAllByDrivingLicenceUserIdAndRequestStatus(userId, RequestStatus.PENDING);

        if (requests.size() == 0){
            return null;
        }

        return drivingLicenceChangeRequestMapper.toDto(requests.get(0));
    }

    @Override
    public Page<DrivingLicenceChangeRequestDTO> findAll(String token, RequestStatus requestStatus, Pageable pageable) {
        String roles = jwtTokenUtil.getRoles(token);

        if (roles.contains("admin")){
            Page<DrivingLicenceChangeRequest> requests = drivingLicenceChangeRequestRepository.findAllByRequestStatus(requestStatus, pageable);
            Page<DrivingLicenceChangeRequestDTO> requestsDTO = requests.map(drivingLicenceChangeRequestMapper::toDto);
            return requestsDTO;
        }
        else if (roles.contains("zaposleni")){
            // if status is PENDING there's no reason to filter requests for specific employee
            // PENDING request should be available to every employee
            if (requestStatus == RequestStatus.PENDING){
                Page<DrivingLicenceChangeRequest> requests = drivingLicenceChangeRequestRepository.findAllByRequestStatus(requestStatus, pageable);
                Page<DrivingLicenceChangeRequestDTO> requestsDTO = requests.map(drivingLicenceChangeRequestMapper::toDto);
                return requestsDTO;
            }
            // else
            Page<DrivingLicenceChangeRequest> requests = drivingLicenceChangeRequestRepository.findAllByEmployeeIdAndRequestStatus(jwtTokenUtil.getUserId(token),requestStatus, pageable);
            Page<DrivingLicenceChangeRequestDTO> requestsDTO = requests.map(drivingLicenceChangeRequestMapper::toDto);
            return requestsDTO;
        }
        else if (roles.contains("gradjanin")){
            Page<DrivingLicenceChangeRequest> requests = drivingLicenceChangeRequestRepository.findAllByDrivingLicenceUserIdAndRequestStatus(jwtTokenUtil.getUserId(token), requestStatus, pageable);
            Page<DrivingLicenceChangeRequestDTO> requestsDTO = requests.map(drivingLicenceChangeRequestMapper::toDto);
            return requestsDTO;
        }
        return null;
    }

    @Override
    public DrivingLicenceChangeRequestDTO save(DrivingLicenceChangeRequestDTO drivingLicenceChangeRequestDTO) {
        DrivingLicenceChangeRequest drivingLicenceChangeRequest = drivingLicenceChangeRequestMapper.toEntity(drivingLicenceChangeRequestDTO);
        return drivingLicenceChangeRequestMapper.toDto(drivingLicenceChangeRequestRepository.save(drivingLicenceChangeRequest));
    }

    @Override
    public void delete(Long id) {
        drivingLicenceChangeRequestRepository.deleteById(id);
    }
}
