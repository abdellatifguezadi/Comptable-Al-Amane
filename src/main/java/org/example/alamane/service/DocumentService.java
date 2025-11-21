package org.example.alamane.service;

import org.example.alamane.DTO.requestDto.DocumentUploadRequest;
import org.example.alamane.DTO.responseDTO.DocumentResponse;

import java.util.List;

public interface DocumentService {
    DocumentResponse uploadDocument(DocumentUploadRequest request, String userEmail);
    List<DocumentResponse> getDocumentsBySocieteAndExercice(Long societeId, int exercice);
}
