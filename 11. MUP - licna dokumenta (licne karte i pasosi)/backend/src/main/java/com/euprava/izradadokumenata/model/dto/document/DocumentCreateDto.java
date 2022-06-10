package com.euprava.izradadokumenata.model.dto.document;

import com.euprava.izradadokumenata.model.DocumentType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentCreateDto {

    private String issuingAuthority;
    private LocalDate expirationDate;
    private DocumentType documentType;
}
