package ftn.euprava.mupvozila.util.mapper;

import ftn.euprava.mupvozila.model.RegistrationCertificate;
import ftn.euprava.mupvozila.web.dto.RegistrationCertificateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CarMapper.class})
public interface RegistrationCertificateMapper {

    @Mapping(target = "userDTO", source = "user")
    @Mapping(target = "carDTO", source = "car")
    RegistrationCertificateDTO toDto(RegistrationCertificate registrationCertificate);

    @Mapping(target = "user", source = "userDTO")
    @Mapping(target = "car", source = "carDTO")
    RegistrationCertificate toEntity(RegistrationCertificateDTO registrationCertificateDTO);
}
