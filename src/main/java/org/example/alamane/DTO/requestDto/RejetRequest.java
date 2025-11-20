package org.example.alamane.DTO.requestDto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class RejetRequest {
    @NotBlank(message = "Le motif de rejet est obligatoire")
    private String motif;
}
