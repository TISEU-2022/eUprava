package com.euprava.izradadokumenata.repository;

import com.euprava.izradadokumenata.model.DocumentAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentAppointmentRepo extends JpaRepository<DocumentAppointment, String> {
}
