package com.euprava.izradadokumenata.model.dto.documentAppointment;

import com.euprava.izradadokumenata.model.dto.user.SimpleUserDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SimpleDocumentAppointmentDto {

    private SimpleUserDto user;

    private LocalDateTime appointmentTime;

    private Boolean appointmentForMinor;
}
