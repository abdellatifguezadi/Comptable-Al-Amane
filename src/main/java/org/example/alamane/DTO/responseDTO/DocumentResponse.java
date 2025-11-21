package org.example.alamane.DTO.responseDTO;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DocumentResponse {
    private Long id;
    private String numeroPiece;
    private String type;
    private String categorie;
    private LocalDate datePiece;
    private BigDecimal montant;
    private String fournisseur;
    private String statut;
    private String commentaire;
    private String fichierUrl;
    private String societeRaisonSociale;
    private LocalDateTime dateCreation;
}
