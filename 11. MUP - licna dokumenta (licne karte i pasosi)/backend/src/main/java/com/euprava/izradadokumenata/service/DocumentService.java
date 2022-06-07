package com.euprava.izradadokumenata.service;

import com.euprava.izradadokumenata.model.Document;
import com.euprava.izradadokumenata.model.dto.document.DocumentCreateDto;
import com.euprava.izradadokumenata.model.dto.document.DocumentUpdateDto;

import java.util.List;

public interface DocumentService {

    Document getDocumentById(Long id);

    List<Document> getAll();

    Document createDocument(DocumentCreateDto documentDto);

    Document updateDocument(Long id, DocumentUpdateDto documentUpdateDto);

    void removeDocument(Long documentId);

}
