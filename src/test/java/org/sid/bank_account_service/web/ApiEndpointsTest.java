package org.sid.bank_account_service.web;

import org.sid.bank_account_service.entities.Compte;
import org.sid.bank_account_service.entities.TypeCompte;
import org.sid.bank_account_service.repositories.CompteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Verifie Swagger (OpenAPI), Spring Data Rest + projection, et GraphQL. */
@SpringBootTest
@AutoConfigureMockMvc
class ApiEndpointsTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompteRepository compteRepository;

    @BeforeEach
    void init() {
        compteRepository.deleteAll();
        compteRepository.save(Compte.builder().solde(1500).dateCreation(LocalDate.now()).type(TypeCompte.EPARGNE).build());
    }

    @Test
    void openApiDocumentationIsGenerated() throws Exception {
        mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("/api/comptes")));
    }

    @Test
    void dataRestProjection() throws Exception {
        mockMvc.perform(get("/datarest/comptes?projection=mobile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.comptes[0].solde").value(1500.0))
                .andExpect(jsonPath("$._embedded.comptes[0].type").value("EPARGNE"));
    }

    @Test
    void dataRestSearchByType() throws Exception {
        mockMvc.perform(get("/datarest/comptes/search/byType?t=EPARGNE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.comptes[0].solde").value(1500.0));
    }

    @Test
    void graphqlQuery() throws Exception {
        mockMvc.perform(post("/graphql")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"query\":\"{ allComptes { solde type } }\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.allComptes[0].solde").value(1500.0))
                .andExpect(jsonPath("$.data.allComptes[0].type").value("EPARGNE"));
    }

    @Test
    void graphqlMutation() throws Exception {
        mockMvc.perform(post("/graphql")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"query\":\"mutation { saveCompte(compte: {solde: 4200, type: COURANT}) { id solde type } }\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.saveCompte.solde").value(4200.0))
                .andExpect(jsonPath("$.data.saveCompte.type").value("COURANT"));
    }
}
