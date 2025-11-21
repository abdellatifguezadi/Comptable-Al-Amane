package org.example.alamane.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.alamane.DTO.requestDto.DocumentUploadRequest;
import org.example.alamane.DTO.responseDTO.DocumentResponse;
import org.example.alamane.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('SOCIETE')")
    public ResponseEntity<DocumentResponse> uploadDocument(
            @Valid @ModelAttribute DocumentUploadRequest request,
            Authentication authentication) {
        DocumentResponse response = documentService.uploadDocument(request, authentication.getName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/societe/{societeId}/exercice/{exercice}")
    @PreAuthorize("hasRole('SOCIETE')")
    public ResponseEntity<List<DocumentResponse>> getDocuments(
            @PathVariable Long societeId,
            @PathVariable int exercice) {
        List<DocumentResponse> documents = documentService.getDocumentsBySocieteAndExercice(societeId, exercice);
        return ResponseEntity.ok(documents);
    }
}
