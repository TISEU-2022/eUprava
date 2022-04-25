package ftn.euprava.mupvozila.service.implementation;

import ftn.euprava.mupvozila.model.Car;
import ftn.euprava.mupvozila.repository.CarRepository;
import ftn.euprava.mupvozila.service.ICarService;
import ftn.euprava.mupvozila.util.mapper.CarMapper;
import ftn.euprava.mupvozila.web.dto.CarDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements ICarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public Car findOne(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public CarDTO save(CarDTO carDTO) {
        Car car = carRepository.save(carMapper.toEntity(carDTO));
        return carMapper.toDto(car);
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
