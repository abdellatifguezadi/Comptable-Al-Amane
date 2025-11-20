package org.example.alamane.mapper;

import org.example.alamane.DTO.requestDto.DocumentUploadRequest;
import org.example.alamane.DTO.responseDTO.DocumentResponse;
import org.example.alamane.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    @Mapping(target = "societeRaisonSociale", source = "societe.raisonSociale")
    DocumentResponse toResponse(Document document);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "statut", ignore = true)
    @Mapping(target = "commentaire", ignore = true)
    @Mapping(target = "fichierUrl", ignore = true)
    @Mapping(target = "societe", ignore = true)
    @Mapping(target = "dateCréation", ignore = true)
    @Mapping(target = "dateModification", ignore = true)
    Document toEntity(DocumentUploadRequest request);
}
