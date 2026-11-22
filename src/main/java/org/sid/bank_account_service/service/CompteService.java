package org.sid.bank_account_service.service;

import org.sid.bank_account_service.dto.CompteRequestDTO;
import org.sid.bank_account_service.dto.CompteResponseDTO;

import java.util.List;

/** Couche metier du micro-service. */
public interface CompteService {
    CompteResponseDTO addCompte(CompteRequestDTO compteDTO);

    CompteResponseDTO updateCompte(Long id, CompteRequestDTO compteDTO);

    CompteResponseDTO getCompte(Long id);

    List<CompteResponseDTO> getAllComptes();

    void deleteCompte(Long id);

    double totalSolde();
}
