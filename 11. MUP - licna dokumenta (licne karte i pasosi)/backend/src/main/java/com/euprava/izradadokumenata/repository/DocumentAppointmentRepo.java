package com.euprava.izradadokumenata.repository;

import com.euprava.izradadokumenata.model.DocumentAppointment;
import com.euprava.izradadokumenata.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentAppointmentRepo extends JpaRepository<DocumentAppointment, String> {
    Optional<DocumentAppointment> findDocumentAppointmentByRequestedAppointmentTimeIsBetweenAndDocumentDocumentType(LocalDateTime appointmentStart, LocalDateTime appointmentEnd, DocumentType documentType);

    List<DocumentAppointment> findDocumentAppointmentsByUserUsername(String username);

}
