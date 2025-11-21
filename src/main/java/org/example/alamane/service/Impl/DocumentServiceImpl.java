package org.example.alamane.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.alamane.DTO.requestDto.DocumentUploadRequest;
import org.example.alamane.DTO.responseDTO.DocumentResponse;
import org.example.alamane.entity.Document;
import org.example.alamane.entity.Utilisateur;
import org.example.alamane.enums.StatutDocument;
import org.example.alamane.enums.TypeDocument;
import org.example.alamane.mapper.DocumentMapper;
import org.example.alamane.repository.DocumentRepository;
import org.example.alamane.repository.UtilisateurRepository;
import org.example.alamane.service.DocumentService;
import org.example.alamane.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final FileStorageService fileStorageService;
    private final DocumentMapper documentMapper;

    @Override
    @Transactional
    public DocumentResponse uploadDocument(DocumentUploadRequest request, String userEmail) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (utilisateur.getSociete() == null) {
            throw new RuntimeException("Utilisateur non rattaché à une société");
        }

        String fileName = fileStorageService.storeFile(request.getFichier());

        Document document = documentMapper.toEntity(request);
        document.setFichierUrl(fileName);
        document.setStatut(StatutDocument.EN_ATTENTE);
        document.setSociete(utilisateur.getSociete());
        document.setDateCreation(LocalDateTime.now());

        Document saved = documentRepository.save(document);
        return documentMapper.toResponse(saved);
    }

    @Override
    public List<DocumentResponse> getDocumentsBySocieteAndExercice(String userEmail, int exercice) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouve"));
        List<Document> documents = documentRepository.findBySocieteIdAndExercice(utilisateur.getSociete().getId(), exercice);
        return documents.stream()
                .map(documentMapper::toResponse)
                .toList();
    }

    @Override
    public List<DocumentResponse> getDocumentsEnAttente() {
        List<Document> documents = documentRepository.findByStatut(StatutDocument.EN_ATTENTE);
        return documents.stream()
                .map(documentMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public DocumentResponse validerDocument(Long documentId, String commentaire) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document non trouvé"));

        if(document.getStatut() != StatutDocument.EN_ATTENTE) {
            throw new RuntimeException("Le document n'est pas en attente de validation");
        };

        document.setStatut(StatutDocument.VALIDE);
        document.setCommentaire(commentaire);
        document.setDateValidation(LocalDateTime.now());
        document.setDateModification(LocalDateTime.now());
        
        Document saved = documentRepository.save(document);
        return documentMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public DocumentResponse rejeterDocument(Long documentId, String motif) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document non trouvé"));

        if(document.getStatut() != StatutDocument.EN_ATTENTE) {
            throw new RuntimeException("Le document n'est pas en attente de validation");
        };
        document.setStatut(StatutDocument.REJETE);
        document.setCommentaire(motif);
        document.setDateValidation(LocalDateTime.now());
        document.setDateModification(LocalDateTime.now());
        
        Document saved = documentRepository.save(document);
        return documentMapper.toResponse(saved);
    }

    @Override
    public List<DocumentResponse> getDocumentsBySociete(Long societeId) {
        List<Document> documents = documentRepository.findBySocieteId(societeId);
        return documents.stream()
                .map(documentMapper::toResponse)
                .toList();
    }
}
