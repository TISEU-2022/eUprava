package com.euprava.izradadokumenata.model.dto.document;

import com.euprava.izradadokumenata.model.Document;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentMapper {

    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);



    Document from (DocumentCreateDto documentCreateDto);

    Document from (DocumentUpdateDto documentUpdateDto);

    DocumentCreateDto toCreateDto(Document document);

    DocumentUpdateDto toUpdateDto(Document document);

    DocumentSimpleDto toDocumentSimpleDto(Document document);

}
