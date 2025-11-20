package org.example.alamane.DTO.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SocieteRequest {
    @NotBlank(message = "La raison sociale est obligatoire")
    private String raisonSociale;

    @NotBlank(message = "L'ICE est obligatoire")
    private String ice;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format email invalide")
    private String email;
}
