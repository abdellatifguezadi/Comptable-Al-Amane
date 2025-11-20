package org.example.alamane.mapper;

import org.example.alamane.DTO.requestDto.SocieteRequest;
import org.example.alamane.DTO.responseDTO.SocieteResponse;
import org.example.alamane.entity.Societe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SocieteMapper {

    SocieteResponse toResponse(Societe societe);

    @Mapping(target = "id", ignore = true)
    Societe toEntity(SocieteRequest request);
}
