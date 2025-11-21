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

        Document document = new Document();
        document.setNumeroPiece(request.getNumeroPiece());
        document.setType(TypeDocument.valueOf(request.getType()));
        document.setCategorie(request.getCategorie());
        document.setDatePiece(request.getDatePiece());
        document.setMontant(request.getMontant());
        document.setFournisseur(request.getFournisseur());
        document.setFichierUrl(fileName);
        document.setStatut(StatutDocument.EN_ATTENTE);
        document.setSociete(utilisateur.getSociete());
        document.setDateCréation(LocalDateTime.now());

        Document saved = documentRepository.save(document);
        return documentMapper.toResponse(saved);
    }

    @Override
    public List<DocumentResponse> getDocumentsBySocieteAndExercice(Long societeId, int exercice) {
        List<Document> documents = documentRepository.findBySocieteIdAndExercice(societeId, exercice);
        return documents.stream()
                .map(documentMapper::toResponse)
                .toList();
    }
}
