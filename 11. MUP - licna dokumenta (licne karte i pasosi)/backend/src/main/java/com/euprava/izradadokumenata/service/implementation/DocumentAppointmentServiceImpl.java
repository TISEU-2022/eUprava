package com.euprava.izradadokumenata.service.implementation;

import com.euprava.izradadokumenata.model.Document;
import com.euprava.izradadokumenata.model.DocumentAppointment;
import com.euprava.izradadokumenata.model.DocumentType;
import com.euprava.izradadokumenata.model.User;
import com.euprava.izradadokumenata.model.dto.documentAppointment.DocumentAppointmentDto;
import com.euprava.izradadokumenata.model.dto.documentAppointment.DocumentAppointmentMapper;
import com.euprava.izradadokumenata.model.dto.documentAppointment.DocumentAppointmentUserDto;
import com.euprava.izradadokumenata.repository.DocumentAppointmentRepo;
import com.euprava.izradadokumenata.service.DocumentAppointmentService;
import com.euprava.izradadokumenata.service.DocumentService;
import com.euprava.izradadokumenata.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DocumentAppointmentServiceImpl implements DocumentAppointmentService {

    private final DocumentAppointmentRepo documentAppointmentRepo;

    DocumentService documentService;
    private final UserService userService;

    @Override
    public DocumentAppointment appointmentForSelf(String username, DocumentAppointmentDto appointmentDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        User user = userService.getUserByUsername(username);
        DocumentAppointment documentAppointment = DocumentAppointmentMapper.INSTANCE.from(appointmentDto);
        documentAppointment.setUser(user);
        documentAppointment.setRequestedAppointmentTime(LocalDateTime.parse(appointmentDto.getRequestedAppointmentTime(), formatter));
        Document document = new Document();
        document.setIssuingAuthority("MUP " + user.getMunicipality().toString());
        document.setCreatedOn(LocalDateTime.now());
        if (appointmentDto.getDocumentType() == DocumentType.DOCUMENT_IDCARD) {
            document.setDocumentType(DocumentType.DOCUMENT_IDCARD);
            document.setExpirationDate(LocalDate.now().plusYears(5));
        }
        if (appointmentDto.getDocumentType() == DocumentType.DOCUMENT_PASSPORT) {
            document.setDocumentType(DocumentType.DOCUMENT_PASSPORT);
            document.setExpirationDate(LocalDate.now().plusYears(10));
        }
        documentService.saveDocument(document);
        documentAppointment.setDocument(document);
        return documentAppointmentRepo.save(documentAppointment);
    }

    @Override
    public DocumentAppointment appointmentForMinor(String username, DocumentAppointmentDto appointmentDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        User user = userService.getUserByUsername(username);
        DocumentAppointment documentAppointment = new DocumentAppointment();
        documentAppointment.setAppointmentForMinor(true);
        documentAppointment.setUser(user);
        documentAppointment.setRequestedAppointmentTime(LocalDateTime.parse(appointmentDto.getRequestedAppointmentTime(), formatter));
        Document document = new Document();
        document.setIssuingAuthority("MUP " + user.getMunicipality().toString());
        document.setCreatedOn(LocalDateTime.now());
        if (appointmentDto.getDocumentType() == DocumentType.DOCUMENT_IDCARD) {
            document.setDocumentType(DocumentType.DOCUMENT_IDCARD);
            document.setExpirationDate(LocalDate.now().plusYears(5));
        }
        if (appointmentDto.getDocumentType() == DocumentType.DOCUMENT_PASSPORT) {
            document.setDocumentType(DocumentType.DOCUMENT_PASSPORT);
            document.setExpirationDate(LocalDate.now().plusYears(10));
        }
        User additionalUser = User.builder().citizenship(appointmentDto.getMinorCitizenship()).countryOfBirth(appointmentDto.getMinorCountryOfBirth())
                .dateOfBirth(appointmentDto.getMinorDateOfBirth()).gender(appointmentDto.getMinorGender()).jmbg(appointmentDto.getMinorJmbg())
                .username("temp_" + appointmentDto.getMinorFirstName() + "_" + appointmentDto.getMinorLastName() + "_" + System.currentTimeMillis())
                .name(appointmentDto.getMinorFirstName()).lastName(appointmentDto.getMinorLastName()).build();
        userService.saveUser(additionalUser);
        documentAppointment.setAdditionalUser(additionalUser);
        documentService.saveDocument(document);
        documentAppointment.setDocument(document);
        return documentAppointmentRepo.save(documentAppointment);
    }

    @Override
    public List<DocumentAppointment> getAll() {
        return documentAppointmentRepo.findAll();
    }

    @Override
    public List<DocumentAppointmentUserDto> getAllAppointmentsForUser(String username) {
        List<DocumentAppointment> appointments = documentAppointmentRepo.findDocumentAppointmentsByUserUsername(username);
        List<DocumentAppointmentUserDto> appointmentUserDtos = new ArrayList<>();

        for (DocumentAppointment a : appointments) {
            DocumentAppointmentUserDto dto = DocumentAppointmentUserDto.builder()
                    .appointmentForMinor(a.isAppointmentForMinor()).appointmentTime(a.getRequestedAppointmentTime())
                    .documentType(a.getDocument().getDocumentType()).username(username).build();
            appointmentUserDtos.add(dto);
        }
        return appointmentUserDtos;
    }

    @Override
    public boolean isAppointmentAvailable(String appointmentTime, DocumentType documentType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(appointmentTime, formatter);
        log.info("\n \n TIME VERIFICATION : \n" + startTime.minusMinutes(15) + startTime.plusMinutes(15) + documentType);
        if (documentAppointmentRepo.findDocumentAppointmentByRequestedAppointmentTimeIsBetweenAndDocumentDocumentType(startTime.minusMinutes(15), startTime.plusMinutes(15), documentType).isPresent()) {
            return false;
        } else return true;
    }
}
