package ftn.euprava.zdravstvo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ftn.euprava.zdravstvo.api.dto.BirthCertificateMaticarRequest;
import ftn.euprava.zdravstvo.api.dto.BirthCertificateRequest;
import ftn.euprava.zdravstvo.api.dto.MaticarCertificateResponse;
import ftn.euprava.zdravstvo.api.dto.ParentsMaticarRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class UserService {

    private static final String MATICAR_URI = "http://localhost:4002/";

    public ResponseEntity<MaticarCertificateResponse> addBirthCertificate(final BirthCertificateRequest request) {
        try {
            ResponseEntity<MaticarCertificateResponse> birthCertificateResponse = sendBirthCertificateRequest(request);
            if (birthCertificateResponse.getStatusCode().is2xxSuccessful()) {
                ResponseEntity<MaticarCertificateResponse> addParentsIdsResponse = addParentsIdsRequests((request));
                if (birthCertificateResponse.getStatusCode().is2xxSuccessful()) {
                    return new ResponseEntity<>(new MaticarCertificateResponse("success"), HttpStatus.OK);
                }
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(new MaticarCertificateResponse("failed"), BAD_REQUEST);
        }
        return new ResponseEntity<>(new MaticarCertificateResponse("failed"), BAD_REQUEST);
    }

    private ResponseEntity<MaticarCertificateResponse> sendBirthCertificateRequest(final BirthCertificateRequest request) {
        final BirthCertificateMaticarRequest maticarRequest = BirthCertificateMaticarRequest.of(request);

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
                    MATICAR_URI,
                    HttpMethod.POST,
                    entity,
                    MaticarCertificateResponse.class
            );
        }
        catch (Exception e) {
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

    private ResponseEntity<MaticarCertificateResponse> recordDeceasedCitizen(final String jmbg) {
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
}
