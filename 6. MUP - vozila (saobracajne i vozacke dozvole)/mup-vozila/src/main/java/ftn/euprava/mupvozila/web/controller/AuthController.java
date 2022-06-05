package ftn.euprava.mupvozila.web.controller;

import ftn.euprava.mupvozila.util.jwt.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping(value = "/validate-token")
    public ResponseEntity<Boolean> validateToken(HttpServletRequest request){
        // if token is valid then this controller will be called
        // so there's no need to do another validation of token
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

}
