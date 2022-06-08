package com.euprava.izradadokumenata.service;

import com.euprava.izradadokumenata.model.DocumentAppointment;
import com.euprava.izradadokumenata.model.dto.documentAppointment.DocumentAppointmentDto;
import com.euprava.izradadokumenata.model.dto.user.AdditionalUserDto;
import com.euprava.izradadokumenata.model.dto.user.LoggedUserDto;

public interface DocumentAppointmentService {

    DocumentAppointment appointmentForSelf(LoggedUserDto user, DocumentAppointmentDto appointmentDto);

    DocumentAppointment appointmentForMinor(LoggedUserDto requestingUser, DocumentAppointmentDto appointmentDto, AdditionalUserDto additionalUserDto);
}
