package com.euprava.izradadokumenata.model.dto.document;

import com.euprava.izradadokumenata.model.DocumentType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DocumentUpdateDto {

    @NotBlank
    private String issuingAuthority;

    @NotBlank
    private DocumentType documentType;


}
