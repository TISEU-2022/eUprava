package ftn.euprava.mupvozila.util.mapper;

import ftn.euprava.mupvozila.model.RequestForDrivingLicence;
import ftn.euprava.mupvozila.web.dto.RequestForDrivingLicenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, DrivingLicenceMapper.class})
public interface RequestForDrivingLicenceMapper {
    @Mapping(target = "drivingLicenceDTO", source = "drivingLicence")
    RequestForDrivingLicenceDTO toDto(RequestForDrivingLicence drivingLicence);

    @Mapping(target = "drivingLicence", source = "drivingLicenceDTO")
    RequestForDrivingLicence toEntity(RequestForDrivingLicenceDTO requestForDrivingLicenceDTO);
}
