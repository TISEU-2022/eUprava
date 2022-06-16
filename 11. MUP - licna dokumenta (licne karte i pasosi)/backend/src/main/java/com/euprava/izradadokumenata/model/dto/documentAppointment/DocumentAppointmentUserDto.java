package com.euprava.izradadokumenata.model.dto.documentAppointment;


import com.euprava.izradadokumenata.model.DocumentType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DocumentAppointmentUserDto {
    private String username;

    private LocalDateTime appointmentTime;

    private DocumentType documentType;

    private Boolean appointmentForMinor;
}
