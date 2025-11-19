package org.example.alamane.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Societe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String raisonSociale;
    private String ice;
    private String adresse;
    private String telephone;
    private String email;
}
