package com.euprava.izradadokumenata.service;

import com.euprava.izradadokumenata.model.Document;
import com.euprava.izradadokumenata.model.dto.document.DocumentCreateDto;
import com.euprava.izradadokumenata.model.dto.document.DocumentUpdateDto;

import java.util.List;

public interface DocumentService {

    Document getDocumentById(String id);

    List<Document> getAll();

    Document createDocument(DocumentCreateDto documentDto);

    Document updateDocument(String id, DocumentUpdateDto documentUpdateDto);

    void removeDocument(String documentId);

}
