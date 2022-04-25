package ftn.euprava.mupvozila.util.mapper;

import ftn.euprava.mupvozila.model.RequestForDrivingLicence;
import ftn.euprava.mupvozila.web.dto.RequestForDrivingLicenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RequestForDrivingLicenceMapper {

    RequestForDrivingLicenceDTO toDto(RequestForDrivingLicence drivingLicence);

    RequestForDrivingLicence toEntity(RequestForDrivingLicenceDTO requestForDrivingLicenceDTO);
}
