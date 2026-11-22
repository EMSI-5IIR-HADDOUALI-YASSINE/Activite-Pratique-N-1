package org.sid.bank_account_service.web;

import org.sid.bank_account_service.repositories.CompteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CompteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompteRepository compteRepository;

    @BeforeEach
    void init() {
        compteRepository.deleteAll();
    }

    @Test
    void createThenList() throws Exception {
        mockMvc.perform(post("/api/comptes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"solde\":2500,\"type\":\"EPARGNE\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.type").value("EPARGNE"));

        mockMvc.perform(get("/api/comptes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void unknownCompteIs404() throws Exception {
        mockMvc.perform(get("/api/comptes/9999")).andExpect(status().isNotFound());
        mockMvc.perform(delete("/api/comptes/9999")).andExpect(status().isNotFound());
    }
}
