package com.euprava.izradadokumenata.service.implementation;

import com.euprava.izradadokumenata.model.Document;
import com.euprava.izradadokumenata.model.dto.document.DocumentCreateDto;
import com.euprava.izradadokumenata.model.dto.document.DocumentMapper;
import com.euprava.izradadokumenata.model.dto.document.DocumentUpdateDto;
import com.euprava.izradadokumenata.repository.DocumentRepo;
import com.euprava.izradadokumenata.service.DocumentService;
import com.euprava.izradadokumenata.util.exceptions.DocumentMissingException;
import com.euprava.izradadokumenata.util.exceptions.DocumentRemovedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepo documentRepo;

    @Override
    public Document getDocumentById(Long id) {
        Optional<Document> document = documentRepo.findById(id);
        if (document.isEmpty()) {
            throw new DocumentMissingException();
        }
        return document.get();
    }

    @Override
    public List<Document> getAll() {
        return documentRepo.findAll();
    }

    @Override
    public Document createDocument(DocumentCreateDto documentDto) {
        Document document = DocumentMapper.INSTANCE.from(documentDto);
        document.setCreatedOn(LocalDateTime.now());
        return documentRepo.save(document);
    }

    @Override
    public Document updateDocument(Long id, DocumentUpdateDto documentUpdateDto) {
        Document document = getDocumentById(id);
        document.setDocumentType(documentUpdateDto.getDocumentType());
        document.setIssuingAuthority(document.getIssuingAuthority());
        return documentRepo.save(document);
    }

    @Override
    public void removeDocument(Long documentId) {
        Document document = getDocumentById(documentId);
        if (document.isRemoved()) {
            throw new DocumentRemovedException();
        }
        document.setRemoved(true);
    }
}
