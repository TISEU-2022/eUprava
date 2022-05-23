package ftn.euprava.mupvozila.service.implementation;

import ftn.euprava.mupvozila.model.DrivingLicence;
import ftn.euprava.mupvozila.model.RequestForDrivingLicence;
import ftn.euprava.mupvozila.model.enums.RequestStatus;
import ftn.euprava.mupvozila.repository.RequestForDrivingLicenceRepository;
import ftn.euprava.mupvozila.service.IRequestForDrivingLicenceService;
import ftn.euprava.mupvozila.util.jwt.JwtTokenUtil;
import ftn.euprava.mupvozila.util.mapper.RequestForDrivingLicenceMapper;
import ftn.euprava.mupvozila.web.dto.RequestForDrivingLicenceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestForDrivingLicenceService implements IRequestForDrivingLicenceService {

    private final RequestForDrivingLicenceRepository requestForDrivingLicenceRepository;
    private final RequestForDrivingLicenceMapper requestForDrivingLicenceMapper;
    private final JwtTokenUtil jwtTokenUtil;

    public RequestForDrivingLicenceService(RequestForDrivingLicenceRepository requestForDrivingLicenceRepository,
                                           RequestForDrivingLicenceMapper requestForDrivingLicenceMapper, JwtTokenUtil jwtTokenUtil) {
        this.requestForDrivingLicenceRepository = requestForDrivingLicenceRepository;
        this.requestForDrivingLicenceMapper = requestForDrivingLicenceMapper;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    public RequestForDrivingLicenceDTO findOne(Long id) {

        RequestForDrivingLicence requestForDrivingLicence = requestForDrivingLicenceRepository.findById(id).orElse(null);

        if(requestForDrivingLicence == null)
            throw new RuntimeException();

        return requestForDrivingLicenceMapper.toDto(requestForDrivingLicence);
    }

    @Override
    public RequestForDrivingLicenceDTO findPendingRequest(String userId) {
        List<RequestForDrivingLicence> requests = requestForDrivingLicenceRepository.findAllByCitizenIdEqualsAndRequestStatus(userId, RequestStatus.PENDING);

        if (requests.size() == 0){
            return null;
        }

        return requestForDrivingLicenceMapper.toDto(requests.get(0));
    }

    @Override
    public Page<RequestForDrivingLicenceDTO> findAll(String token, RequestStatus requestStatus, Pageable pageable) {
        String roles = jwtTokenUtil.getRoles(token);
        String userId = jwtTokenUtil.getUserId(token);

        if (roles.contains("admin")){
            Page<RequestForDrivingLicence> requests = requestForDrivingLicenceRepository.findAllByRequestStatus(requestStatus, pageable);
            Page<RequestForDrivingLicenceDTO> requestsDTO = requests.map(requestForDrivingLicenceMapper::toDto);
            return requestsDTO;
        }
        else if (roles.contains("zaposleni")){
            // if status is PENDING there's no reason to filter requests for specific employee
            // PENDING request should be available to every employee
            if (requestStatus == RequestStatus.PENDING){
                Page<RequestForDrivingLicence> requests = requestForDrivingLicenceRepository.findAllByRequestStatus(requestStatus, pageable);
                Page<RequestForDrivingLicenceDTO> requestsDTO = requests.map(requestForDrivingLicenceMapper::toDto);
                return requestsDTO;
            }
            // else
            Page<RequestForDrivingLicence> requests = requestForDrivingLicenceRepository.findAllByEmployeeIdAndRequestStatus(jwtTokenUtil.getUserId(token),requestStatus, pageable);
            Page<RequestForDrivingLicenceDTO> requestsDTO = requests.map(requestForDrivingLicenceMapper::toDto);
            return requestsDTO;
        }
        else if (roles.contains("gradjanin")){
            Page<RequestForDrivingLicence> requests = requestForDrivingLicenceRepository.findAllByCitizenIdEqualsAndRequestStatus(jwtTokenUtil.getUserId(token), requestStatus, pageable);
            Page<RequestForDrivingLicenceDTO> requestsDTO = requests.map(requestForDrivingLicenceMapper::toDto);
            return requestsDTO;
        }
        return null;
    }

    @Override
    public RequestForDrivingLicenceDTO save(RequestForDrivingLicenceDTO requestForDrivingLicenceDTO) {
        RequestForDrivingLicence requestForDrivingLicence = requestForDrivingLicenceMapper.toEntity(requestForDrivingLicenceDTO);
        return requestForDrivingLicenceMapper.toDto(requestForDrivingLicenceRepository.save(requestForDrivingLicence));
    }

    @Override
    public void delete(Long id) {
        requestForDrivingLicenceRepository.deleteById(id);
    }
}
