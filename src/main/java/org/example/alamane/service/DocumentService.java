package org.example.alamane.service;

import org.example.alamane.DTO.requestDto.DocumentUploadRequest;
import org.example.alamane.DTO.responseDTO.DocumentResponse;

import java.util.List;

public interface DocumentService {
    DocumentResponse uploadDocument(DocumentUploadRequest request, String userEmail);
    List<DocumentResponse> getDocumentsBySocieteAndExercice(String userEmail, int exercice);
    List<DocumentResponse> getDocumentsEnAttente();
    DocumentResponse validerDocument(Long documentId, String commentaire);
    DocumentResponse rejeterDocument(Long documentId, String motif);
    List<DocumentResponse> getDocumentsBySociete(Long societeId);
}
