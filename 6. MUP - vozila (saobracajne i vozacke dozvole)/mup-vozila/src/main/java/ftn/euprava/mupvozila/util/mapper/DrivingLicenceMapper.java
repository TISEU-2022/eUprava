package ftn.euprava.mupvozila.util.mapper;

import ftn.euprava.mupvozila.model.DrivingLicence;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface DrivingLicenceMapper {

    DrivingLicenceDTO toDto(DrivingLicence drivingLicence);

    DrivingLicence toEntity(DrivingLicenceDTO drivingLicenceDTO);
}
