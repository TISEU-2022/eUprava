package com.euprava.izradadokumenata.model.dto.document;

import com.euprava.izradadokumenata.model.DocumentType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentSimpleDto {

    private Long id;
    String issuingAuthority;
    private LocalDate expirationDate;
    private DocumentType documentType;
}
