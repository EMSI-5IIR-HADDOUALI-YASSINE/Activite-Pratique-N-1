package org.sid.bank_account_service.dto;

import org.sid.bank_account_service.entities.TypeCompte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/** Donnees renvoyees au client. */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteResponseDTO {
    private Long id;
    private double solde;
    private LocalDate dateCreation;
    private TypeCompte type;
}
