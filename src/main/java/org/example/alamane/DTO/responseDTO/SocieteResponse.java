package org.example.alamane.DTO.responseDTO;

import lombok.Data;

@Data
public class SocieteResponse {
    private Long id;
    private String raisonSociale;
    private String ice;
    private String email;
    private String telephone;
}
