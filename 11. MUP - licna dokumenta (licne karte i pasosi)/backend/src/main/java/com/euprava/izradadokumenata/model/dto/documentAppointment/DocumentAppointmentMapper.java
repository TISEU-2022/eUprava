package com.euprava.izradadokumenata.model.dto.documentAppointment;

import com.euprava.izradadokumenata.model.DocumentAppointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentAppointmentMapper {

    DocumentAppointmentMapper INSTANCE = Mappers.getMapper(DocumentAppointmentMapper.class);

    @Mapping(target = "requestedAppointmentTime", ignore = true)
    DocumentAppointment from(DocumentAppointmentDto documentAppointmentDto);

}
