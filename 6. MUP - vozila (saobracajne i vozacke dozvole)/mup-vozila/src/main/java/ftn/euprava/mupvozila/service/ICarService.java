package ftn.euprava.mupvozila.service;

import ftn.euprava.mupvozila.model.Car;
import ftn.euprava.mupvozila.model.RegistrationCertificate;
import ftn.euprava.mupvozila.web.dto.CarDTO;

import java.util.List;

public interface ICarService {

    Car findOne(Long id);

    List<Car> findAll();

    CarDTO save(CarDTO carDTO, RegistrationCertificate registrationCertificate);

    void delete(Long id);

}
