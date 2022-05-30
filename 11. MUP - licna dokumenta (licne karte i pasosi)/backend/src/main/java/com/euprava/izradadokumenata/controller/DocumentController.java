package com.euprava.izradadokumenata.controller;

import com.euprava.izradadokumenata.model.Document;
import com.euprava.izradadokumenata.model.dto.document.*;
import com.euprava.izradadokumenata.service.DocumentService;
import com.euprava.izradadokumenata.util.exceptions.DocumentMissingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/document")
@AllArgsConstructor
@Slf4j
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping("/")
    public ResponseEntity<List<DocumentSimpleDto>> getDocuments(){
        List<DocumentSimpleDto> documentList = new ArrayList<>();
        documentService.getAll().forEach( document -> {
            documentList.add(DocumentMapper.INSTANCE.toDocumentSimpleDto(document));
        });
    return new ResponseEntity<>(documentList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentSimpleDto> getDocumentById(@PathVariable Long id){
        DocumentSimpleDto documentSimpleDto = DocumentMapper.INSTANCE.toDocumentSimpleDto(documentService.getDocumentById((id)));
        return new ResponseEntity<>(documentSimpleDto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<DocumentCreateDto> addDocument(@RequestParam DocumentCreateDto documentCreateDto){
        Document document = new Document();
        try{
           document = documentService.createDocument(documentCreateDto);
        }catch(RuntimeException e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(DocumentMapper.INSTANCE.toCreateDto(document), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentUpdateDto> updateDocument(@PathVariable Long id, @RequestParam DocumentUpdateDto documentUpdateDto){
        Document doc = new Document();
        try {
            doc = documentService.updateDocument(id, documentUpdateDto);
        }
        catch (DocumentMissingException e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(DocumentMapper.INSTANCE.toUpdateDto(doc), HttpStatus.OK);
    }
}
