package org.example.alamane.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.alamane.enums.StatutDocument;
import org.example.alamane.enums.TypeDocument;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroPiece;

    @Enumerated(EnumType.STRING)
    private TypeDocument type;

    private String categorie;
    private LocalDate datePiece;
    private BigDecimal montant;
    private String fournisseur;

    @Enumerated(EnumType.STRING)
    private StatutDocument statut;

    private String commentaire;
    private String fichierUrl;

    @ManyToOne
    @JoinColumn(name = "societe_id")
    private Societe societe;

    private LocalDateTime dateCréation;
    private LocalDateTime dateModification;
}
