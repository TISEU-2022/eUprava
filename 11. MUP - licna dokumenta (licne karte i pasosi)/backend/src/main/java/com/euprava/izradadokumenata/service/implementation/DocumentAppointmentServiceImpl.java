package com.euprava.izradadokumenata.service.implementation;

import com.euprava.izradadokumenata.model.Document;
import com.euprava.izradadokumenata.model.DocumentAppointment;
import com.euprava.izradadokumenata.model.DocumentType;
import com.euprava.izradadokumenata.model.User;
import com.euprava.izradadokumenata.model.dto.documentAppointment.DocumentAppointmentDto;
import com.euprava.izradadokumenata.model.dto.documentAppointment.DocumentAppointmentMapper;
import com.euprava.izradadokumenata.repository.DocumentAppointmentRepo;
import com.euprava.izradadokumenata.service.DocumentAppointmentService;
import com.euprava.izradadokumenata.service.DocumentService;
import com.euprava.izradadokumenata.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
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
        if (appointmentDto.getDocumentType().toLowerCase().trim() == "idcard") {
            document.setDocumentType(DocumentType.DOCUMENT_IDCARD);
            document.setExpirationDate(LocalDate.now().plusYears(5));
        } else if (appointmentDto.getDocumentType().toLowerCase().trim() == "passport") {
            document.setDocumentType(DocumentType.DOCUMENT_PASSPORT);
            document.setExpirationDate(LocalDate.now().plusYears(10));
        }
        documentService.saveDocument(document);
        return documentAppointmentRepo.save(documentAppointment);
    }

    @Override
    public DocumentAppointment appointmentForMinor(String username, DocumentAppointmentDto appointmentDto) {
        return null;
    }

    @Override
    public List<DocumentAppointment> getAll() {
        return documentAppointmentRepo.findAll();
    }
}
