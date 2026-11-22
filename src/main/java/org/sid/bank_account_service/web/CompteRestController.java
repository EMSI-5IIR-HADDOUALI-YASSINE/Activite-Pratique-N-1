package org.sid.bank_account_service.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sid.bank_account_service.dto.CompteRequestDTO;
import org.sid.bank_account_service.dto.CompteResponseDTO;
import org.sid.bank_account_service.service.CompteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** Web service RESTful : http://localhost:8082/api/comptes */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Comptes", description = "Gestion des comptes bancaires")
public class CompteRestController {

    private final CompteService compteService;

    @GetMapping("/comptes")
    @Operation(summary = "Liste de tous les comptes")
    public List<CompteResponseDTO> allComptes() {
        return compteService.getAllComptes();
    }

    @GetMapping("/comptes/{id}")
    @Operation(summary = "Consulter un compte par son id")
    public CompteResponseDTO getCompte(@PathVariable Long id) {
        return compteService.getCompte(id);
    }

    @PostMapping("/comptes")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creer un compte")
    public CompteResponseDTO save(@RequestBody CompteRequestDTO compte) {
        return compteService.addCompte(compte);
    }

    @PutMapping("/comptes/{id}")
    @Operation(summary = "Modifier un compte")
    public CompteResponseDTO update(@PathVariable Long id, @RequestBody CompteRequestDTO compte) {
        return compteService.updateCompte(id, compte);
    }

    @DeleteMapping("/comptes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un compte")
    public void delete(@PathVariable Long id) {
        compteService.deleteCompte(id);
    }
}
