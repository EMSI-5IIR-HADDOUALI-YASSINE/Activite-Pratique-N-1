package org.sid.bank_account_service.mappers;

import org.sid.bank_account_service.dto.CompteRequestDTO;
import org.sid.bank_account_service.dto.CompteResponseDTO;
import org.sid.bank_account_service.entities.Compte;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {

    public Compte toEntity(CompteRequestDTO dto) {
        return Compte.builder()
                .solde(dto.getSolde())
                .type(dto.getType())
                .build();
    }

    public CompteResponseDTO toDto(Compte compte) {
        return new CompteResponseDTO(
                compte.getId(),
                compte.getSolde(),
                compte.getDateCreation(),
                compte.getType());
    }
}
