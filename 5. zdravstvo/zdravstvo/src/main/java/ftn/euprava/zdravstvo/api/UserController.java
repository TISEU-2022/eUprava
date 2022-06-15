package ftn.euprava.zdravstvo.api;

import ftn.euprava.zdravstvo.api.dto.*;
import ftn.euprava.zdravstvo.model.User;
import ftn.euprava.zdravstvo.service.AuthTokenService;
import ftn.euprava.zdravstvo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/parents")
    private ResponseEntity addParents(@RequestBody BirthCertificateRequest request) {
        return userService.addParentsIdsRequests(request);
    }

    @PostMapping
    private ResponseEntity addBirthCertificate(
            @RequestBody BirthCertificateRequest request
    ) {
        return userService.addBirthCertificate(request);
    }

    @PutMapping("/{jmbg}")
    private ResponseEntity recordDeceasedCitizen(@PathVariable("jmbg") String jmbg) {
        return userService.recordDeceasedCitizen(jmbg);
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfo> getCitizenInfo(Authentication authentication){
        return ResponseEntity.ok().body(userService.getCitizenInfo(authentication));

    }

}
