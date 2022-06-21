package ftn.euprava.mupvozila.util.mapper;

import ftn.euprava.mupvozila.model.Car;
import ftn.euprava.mupvozila.web.dto.CarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDTO toDto(Car car);

    Car toEntity(CarDTO carDTO);
}
