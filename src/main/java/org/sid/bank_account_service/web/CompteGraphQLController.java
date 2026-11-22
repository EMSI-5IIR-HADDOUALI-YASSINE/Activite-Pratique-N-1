package org.sid.bank_account_service.web;

import lombok.RequiredArgsConstructor;
import org.sid.bank_account_service.dto.CompteRequestDTO;
import org.sid.bank_account_service.dto.CompteResponseDTO;
import org.sid.bank_account_service.service.CompteService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/** Web service GraphQL : POST http://localhost:8082/graphql  (GraphiQL : /graphiql) */
@Controller
@RequiredArgsConstructor
public class CompteGraphQLController {

    private final CompteService compteService;

    @QueryMapping
    public List<CompteResponseDTO> allComptes() {
        return compteService.getAllComptes();
    }

    @QueryMapping
    public CompteResponseDTO compteById(@Argument Long id) {
        return compteService.getCompte(id);
    }

    @QueryMapping
    public double totalSolde() {
        return compteService.totalSolde();
    }

    @MutationMapping
    public CompteResponseDTO saveCompte(@Argument CompteRequestDTO compte) {
        return compteService.addCompte(compte);
    }

    @MutationMapping
    public CompteResponseDTO updateCompte(@Argument Long id, @Argument CompteRequestDTO compte) {
        return compteService.updateCompte(id, compte);
    }

    @MutationMapping
    public boolean deleteCompte(@Argument Long id) {
        compteService.deleteCompte(id);
        return true;
    }
}
