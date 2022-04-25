package ftn.euprava.mupvozila.util.mapper;

import ftn.euprava.mupvozila.model.DrivingLicence;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface DrivingLicenceMapper {

    @Mapping(target = "userDTO", source = "user")
    DrivingLicenceDTO toDto(DrivingLicence drivingLicence);

    @Mapping(target = "user", source = "userDTO")
    DrivingLicence toEntity(DrivingLicenceDTO drivingLicenceDTO);
}
