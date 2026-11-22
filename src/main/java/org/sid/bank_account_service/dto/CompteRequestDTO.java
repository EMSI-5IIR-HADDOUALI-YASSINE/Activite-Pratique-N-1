package org.sid.bank_account_service.dto;

import org.sid.bank_account_service.entities.TypeCompte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Donnees envoyees par le client pour creer / modifier un compte. */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteRequestDTO {
    private double solde;
    private TypeCompte type;
}
