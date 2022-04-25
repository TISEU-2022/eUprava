package ftn.euprava.mupvozila.util.mapper;

import ftn.euprava.mupvozila.model.DrivingLicenceChangeRequest;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceChangeRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DrivingLicenceMapper.class})
public interface DrivingLicenceChangeRequestMapper {

    @Mapping(target = "drivingLicenceDTO", source = "drivingLicence")
    DrivingLicenceChangeRequestDTO toDto(DrivingLicenceChangeRequest drivingLicenceChangeRequest);

    @Mapping(target = "drivingLicence", source = "drivingLicenceDTO")
    DrivingLicenceChangeRequest toEntity(DrivingLicenceChangeRequestDTO drivingLicenceChangeRequestDTO);
}
