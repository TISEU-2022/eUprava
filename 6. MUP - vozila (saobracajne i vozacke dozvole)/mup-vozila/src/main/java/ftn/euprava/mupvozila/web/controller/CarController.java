package ftn.euprava.mupvozila.web.controller;

import ftn.euprava.mupvozila.service.ICarService;
import ftn.euprava.mupvozila.web.dto.CarDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/car")
public class CarController {

    private final ICarService iCarService;

    public CarController(ICarService iCarService) {
        this.iCarService = iCarService;
    }

    @PostMapping
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO){
        return new ResponseEntity<>(iCarService.save(carDTO), HttpStatus.CREATED) ;
    }

}
