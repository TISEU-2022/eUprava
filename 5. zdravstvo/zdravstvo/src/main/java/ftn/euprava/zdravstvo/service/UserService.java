package ftn.euprava.zdravstvo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ftn.euprava.zdravstvo.api.dto.BirthCertificateMaticarRequest;
import ftn.euprava.zdravstvo.api.dto.BirthCertificateRequest;
import ftn.euprava.zdravstvo.api.dto.MaticarCertificateResponse;
import ftn.euprava.zdravstvo.api.dto.ParentsMaticarRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@Slf4j
public class UserService {

    private static final String MATICAR_URI = "http://maticar:4002/api/user";

    public ResponseEntity<MaticarCertificateResponse> addBirthCertificate(final BirthCertificateRequest request) {
        try {
            ResponseEntity<MaticarCertificateResponse> birthCertificateResponse = sendBirthCertificateRequest(request);
            if (birthCertificateResponse.getStatusCode().is2xxSuccessful()) {
                log.info("uspesan je prvi");
                if (request.getParent1Id() != null && request.getParent2Id() != null) {
                    ResponseEntity<MaticarCertificateResponse> addParentsIdsResponse = addParentsIdsRequests((request));
                    if (birthCertificateResponse.getStatusCode().is2xxSuccessful()) {
                        return new ResponseEntity<>(new MaticarCertificateResponse("success"), HttpStatus.OK);
                    }
                }
                else {
                    return new ResponseEntity<>(new MaticarCertificateResponse("success"), HttpStatus.OK);
                }
            }
            else {
                log.info("nije uspeo prvi");
                log.info(request.toString());
                log.info(String.valueOf(birthCertificateResponse.getStatusCodeValue()));
                log.info(birthCertificateResponse.getStatusCode().toString());
            }
        }
        catch (Exception e) {
            log.info("dosao u exception, exception: {}", e);
            log.info(String.valueOf(e));
            return new ResponseEntity<>(new MaticarCertificateResponse("failed"), BAD_REQUEST);
        }
        log.info("dosao skroz do poslenjeg failed");
        return new ResponseEntity<>(new MaticarCertificateResponse("failed"), BAD_REQUEST);
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
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

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

    private ResponseEntity<MaticarCertificateResponse> addParentsIdsRequests(
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
}
