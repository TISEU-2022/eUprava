package ftn.euprava.mupvozila.web.dto;

import ftn.euprava.mupvozila.model.FuelType;
import lombok.Data;

@Data
public class CarDTO {

    private Long id;

    private String chassisNumber;

    private String make;

    private String model;

    private Integer engine;

    private Integer horsePower;

    private Integer weight;

    private FuelType fuelType;

}
