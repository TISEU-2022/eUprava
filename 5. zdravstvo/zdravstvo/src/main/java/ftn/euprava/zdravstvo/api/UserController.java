package ftn.euprava.zdravstvo.api;

import ftn.euprava.zdravstvo.api.dto.BirthCertificateRequest;
import ftn.euprava.zdravstvo.api.dto.ReportRequest;
import ftn.euprava.zdravstvo.api.dto.ReportResponse;
import ftn.euprava.zdravstvo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
@Validated
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    private ResponseEntity addBirthCertificate(
            @RequestBody BirthCertificateRequest request
    ) {
        return userService.addBirthCertificate(request);
    }

    @PutMapping("/{jmbg}")
    private ResponseEntity recordDeceasedCitizen(@PathParam("jmbg") String jmbg) {
        return userService.recordDeceasedCitizen(jmbg);
    }

}
