package com.euprava.izradadokumenata.model.dto.documentAppointment;

import com.euprava.izradadokumenata.model.DocumentType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentAppointmentDto {

    private DocumentType documentType;

    private String requestedAppointmentTime;

    private boolean available;

    private boolean appointmentForMinor;

    private String minorFirstName;

    private String minorLastName;

    private LocalDate minorDateOfBirth;

    private String minorCountryOfBirth;

    private String minorCitizenship;

    private String minorGender;

    private String minorJmbg;

}
