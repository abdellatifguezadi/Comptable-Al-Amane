package org.example.alamane.mapper;

import org.example.alamane.DTO.requestDto.DocumentUploadRequest;
import org.example.alamane.DTO.responseDTO.DocumentResponse;
import org.example.alamane.entity.Document;
import org.example.alamane.enums.TypeDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    @Mapping(target = "societeRaisonSociale", source = "societe.raisonSociale")
    DocumentResponse toResponse(Document document);


    Document toEntity(DocumentUploadRequest request);

}
