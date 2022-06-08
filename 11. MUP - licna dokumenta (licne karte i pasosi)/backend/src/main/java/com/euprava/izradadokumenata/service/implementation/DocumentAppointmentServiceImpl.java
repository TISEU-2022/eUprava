package com.euprava.izradadokumenata.service.implementation;

import com.euprava.izradadokumenata.model.DocumentAppointment;
import com.euprava.izradadokumenata.model.dto.documentAppointment.DocumentAppointmentDto;
import com.euprava.izradadokumenata.model.dto.user.AdditionalUserDto;
import com.euprava.izradadokumenata.model.dto.user.LoggedUserDto;
import com.euprava.izradadokumenata.repository.DocumentAppointmentRepo;
import com.euprava.izradadokumenata.service.DocumentAppointmentService;
import com.euprava.izradadokumenata.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DocumentAppointmentServiceImpl implements DocumentAppointmentService {

    private final DocumentAppointmentRepo documentAppointmentRepo;

    private final UserService userService;

    @Override
    public DocumentAppointment appointmentForSelf(LoggedUserDto user, DocumentAppointmentDto appointmentDto) {
        return null;
    }

    @Override
    public DocumentAppointment appointmentForMinor(LoggedUserDto requestingUser, DocumentAppointmentDto appointmentDto, AdditionalUserDto additionalUserDto) {
        return null;
    }
}
