package ftn.euprava.mupvozila.web.controller;

import ftn.euprava.mupvozila.service.ICarService;
import ftn.euprava.mupvozila.service.ITokenService;
import ftn.euprava.mupvozila.util.jwt.JwtTokenUtil;
import ftn.euprava.mupvozila.web.dto.CarDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@CrossOrigin
@RestController
@RequestMapping(value = "auth")
public class AuthController {

    private final ITokenService iTokenService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(ITokenService iTokenService, JwtTokenUtil jwtTokenUtil) {
        this.iTokenService = iTokenService;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @GetMapping(value = "jwt")
    public ResponseEntity<String> saveJWT(@PathParam(value = "token") String token){
        // if token already exist in database or if it's not valid token
        // return UNAUTHORIZED to user
        if (iTokenService.findAll().size() > 1 || !jwtTokenUtil.tokenVerified(token)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED) ;
        }

        jwtTokenUtil.getRoles(token);

        return new ResponseEntity<String>(iTokenService.save(token),HttpStatus.OK) ;
    }

    @GetMapping(value = "collect_jwt")
    public ResponseEntity<String> getJWT(){
        if (iTokenService.findAll().size() == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }

        String token = iTokenService.findToken();
        iTokenService.deleteAll();

        return new ResponseEntity<String>(token,HttpStatus.OK) ;
    }

}
