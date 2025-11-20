package org.example.alamane.DTO.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DocumentUploadRequest {
    @NotBlank(message = "Le numéro de pièce est obligatoire")
    private String numeroPiece;

    @NotBlank(message = "Le type est obligatoire")
    private String type;

    @NotBlank(message = "La catégorie est obligatoire")
    private String categorie;

    @NotNull(message = "La date de pièce est obligatoire")
    private LocalDate datePiece;

    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private BigDecimal montant;

    @NotBlank(message = "Le fournisseur est obligatoire")
    private String fournisseur;

    @NotNull(message = "Le fichier est obligatoire")
    private MultipartFile fichier;
}
