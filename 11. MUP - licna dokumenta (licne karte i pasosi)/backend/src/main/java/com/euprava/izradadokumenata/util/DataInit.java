package com.euprava.izradadokumenata.util;

import com.euprava.izradadokumenata.model.*;
import com.euprava.izradadokumenata.repository.DocumentAppointmentRepo;
import com.euprava.izradadokumenata.repository.DocumentRepo;
import com.euprava.izradadokumenata.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class DataInit implements ApplicationRunner {

    private final DocumentRepo documentRepo;
    private final UserRepo userRepo;

    private final DocumentAppointmentRepo documentAppointmentRepo;

    @Override
    public void run(ApplicationArguments args) {
        log.info("OVO SE IZVRSAVA");
        User user = User.builder().password("1234").municipality(Municipality.ALIBUNAR).username("username").citizenship("Serbian").countryOfBirth("Serbia").gender("Male").build();
        userRepo.save(user);
        Document document = Document.builder().documentType(DocumentType.DOCUMENT_IDCARD).expirationDate(LocalDate.of(2020, 10, 20))
                .issuingAuthority("Opstina indasdasdjsija").build();
        documentRepo.save(document);
        DocumentAppointment documentAppointment = DocumentAppointment.builder().appointmentForMinor(false).document(document)
                .requestedAppointmentTime(LocalDateTime.now()).user(user).available(true).build();
        documentAppointmentRepo.save(documentAppointment);

    }
}
