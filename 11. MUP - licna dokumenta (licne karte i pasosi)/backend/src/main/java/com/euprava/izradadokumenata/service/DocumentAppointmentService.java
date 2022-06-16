package com.euprava.izradadokumenata.service;

import com.euprava.izradadokumenata.model.DocumentAppointment;
import com.euprava.izradadokumenata.model.DocumentType;
import com.euprava.izradadokumenata.model.dto.documentAppointment.DocumentAppointmentDto;
import com.euprava.izradadokumenata.model.dto.documentAppointment.DocumentAppointmentUserDto;

import java.util.List;

public interface DocumentAppointmentService {

    DocumentAppointment appointmentForSelf(String username, DocumentAppointmentDto appointmentDto);

    DocumentAppointment appointmentForMinor(String username, DocumentAppointmentDto appointmentDto);

    List<DocumentAppointment> getAll();

    List<DocumentAppointmentUserDto> getAllAppointmentsForUser(String username);

    boolean isAppointmentAvailable(String appointmentTime, DocumentType documentType);
}
