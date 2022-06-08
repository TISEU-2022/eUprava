package com.euprava.izradadokumenata.util;

import com.euprava.izradadokumenata.model.Document;
import com.euprava.izradadokumenata.model.DocumentType;
import com.euprava.izradadokumenata.repository.DocumentRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class DataInit implements ApplicationRunner {

    private final DocumentRepo documentRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Document document = Document.builder().documentType(DocumentType.DOCUMENT_IDCARD).expirationDate(LocalDate.of(2020, 10, 20))
                .issuedOn(LocalDate.now()).issuingAuthority("Opstina indjsija").build();
        documentRepo.save(document);
    }
}
