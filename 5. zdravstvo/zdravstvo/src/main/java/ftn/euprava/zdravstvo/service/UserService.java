package ftn.euprava.zdravstvo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ftn.euprava.zdravstvo.api.dto.*;
import ftn.euprava.zdravstvo.model.User;
import ftn.euprava.zdravstvo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@Slf4j
public class UserService {

    final private UserRepository userRepository;

    private static final String MATICAR_URI = "http://localhost:4002/api/user";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<MaticarCertificateResponse> addBirthCertificate(final BirthCertificateRequest request) {
        log.info("REQUEST: ", request);
        return sendBirthCertificateRequest(request);
    }

    public ResponseEntity<MaticarCertificateResponse> recordDeceasedCitizen(final String jmbg) {
        try {
            final HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            final RestTemplate restTemplate = new RestTemplate();
            return restTemplate.exchange(
                    MATICAR_URI + "/" + jmbg,
                    HttpMethod.PUT,
                    null,
                    MaticarCertificateResponse.class
            );
        }
        catch (Exception e) {
            return new ResponseEntity(new MaticarCertificateResponse("failed"), BAD_REQUEST);
        }
    }

    private ResponseEntity<MaticarCertificateResponse> sendBirthCertificateRequest(final BirthCertificateRequest request) {
        final BirthCertificateMaticarRequest maticarRequest = BirthCertificateMaticarRequest.of(request);

        final Optional<Map<String, Object>> requestBody = this.requestToMap(maticarRequest);
        if (requestBody.isEmpty()) {
            log.info("nije dobar request body");
            return new ResponseEntity(new MaticarCertificateResponse("failed"), BAD_REQUEST);
        }

        try {
            final HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.ALL));

            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody.get(), headers);
            return restTemplate.exchange(
                    MATICAR_URI,
                    HttpMethod.POST,
                    entity,
                    MaticarCertificateResponse.class
            );
        }
        catch (Exception e) {
            log.info("exception u ivketovom rquestu , ");
            log.info(e.getMessage());
            return new ResponseEntity(new MaticarCertificateResponse("failed"), BAD_REQUEST);
        }

    }

    public ResponseEntity<MaticarCertificateResponse> addParentsIdsRequests(
            final BirthCertificateRequest request
    ) {
        final ParentsMaticarRequest maticarRequest = ParentsMaticarRequest.of(request);

        final Optional<Map<String, Object>> requestBody = this.requestToMap(maticarRequest);
        if (requestBody.isEmpty()) {
            return new ResponseEntity(new MaticarCertificateResponse("failed"), BAD_REQUEST);
        }

        try {
            final HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody.get(), headers);
            return restTemplate.exchange(
                    MATICAR_URI + "/parents/" + request.getIdentificationNumber(),
                    HttpMethod.POST,
                    entity,
                    MaticarCertificateResponse.class
            );
        }
        catch (Exception e) {
            return new ResponseEntity(new MaticarCertificateResponse("failed"), BAD_REQUEST);
        }
    }

    private Optional<Map<String, Object>> requestToMap(final Object request) {
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            final String requestAsJson = objectMapper.writeValueAsString(request);
            return Optional.of(objectMapper.readValue(requestAsJson, Map.class));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User getLogged(Authentication authentication){
        return userRepository.findByUsername(authentication.getName());
    }


    public UserInfo getCitizenInfo(Authentication authentication){
        return new UserInfo(userRepository.findByUsername(authentication.getName()));
    }
}
