package org.sid.bank_account_service.service;

import lombok.RequiredArgsConstructor;
import org.sid.bank_account_service.dto.CompteRequestDTO;
import org.sid.bank_account_service.dto.CompteResponseDTO;
import org.sid.bank_account_service.entities.Compte;
import org.sid.bank_account_service.exceptions.CompteNotFoundException;
import org.sid.bank_account_service.mappers.CompteMapper;
import org.sid.bank_account_service.repositories.CompteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;

    @Override
    public CompteResponseDTO addCompte(CompteRequestDTO compteDTO) {
        Compte compte = compteMapper.toEntity(compteDTO);
        compte.setDateCreation(LocalDate.now());
        return compteMapper.toDto(compteRepository.save(compte));
    }

    @Override
    public CompteResponseDTO updateCompte(Long id, CompteRequestDTO compteDTO) {
        Compte compte = compteRepository.findById(id).orElseThrow(() -> new CompteNotFoundException(id));
        compte.setSolde(compteDTO.getSolde());
        compte.setType(compteDTO.getType());
        return compteMapper.toDto(compteRepository.save(compte));
    }

    @Override
    @Transactional(readOnly = true)
    public CompteResponseDTO getCompte(Long id) {
        return compteRepository.findById(id)
                .map(compteMapper::toDto)
                .orElseThrow(() -> new CompteNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompteResponseDTO> getAllComptes() {
        return compteRepository.findAll().stream().map(compteMapper::toDto).toList();
    }

    @Override
    public void deleteCompte(Long id) {
        if (!compteRepository.existsById(id)) {
            throw new CompteNotFoundException(id);
        }
        compteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public double totalSolde() {
        return compteRepository.findAll().stream().mapToDouble(Compte::getSolde).sum();
    }
}
