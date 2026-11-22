package org.sid.bank_account_service.repositories;

import org.sid.bank_account_service.entities.Compte;
import org.sid.bank_account_service.entities.TypeCompte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/** Etape 4 : test de la couche DAO. */
@DataJpaTest
class CompteRepositoryTest {

    @Autowired
    private CompteRepository compteRepository;

    @BeforeEach
    void init() {
        compteRepository.deleteAll();
    }

    @Test
    void saveAndFindById() {
        Compte saved = compteRepository.save(
                Compte.builder().solde(1000).dateCreation(LocalDate.now()).type(TypeCompte.COURANT).build());
        assertThat(saved.getId()).isNotNull();
        assertThat(compteRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void findByType() {
        compteRepository.save(Compte.builder().solde(1).dateCreation(LocalDate.now()).type(TypeCompte.COURANT).build());
        compteRepository.save(Compte.builder().solde(2).dateCreation(LocalDate.now()).type(TypeCompte.EPARGNE).build());
        compteRepository.save(Compte.builder().solde(3).dateCreation(LocalDate.now()).type(TypeCompte.EPARGNE).build());

        List<Compte> epargne = compteRepository.findByType(TypeCompte.EPARGNE);
        assertThat(epargne).hasSize(2);
    }

    @Test
    void deleteCompte() {
        Compte c = compteRepository.save(Compte.builder().solde(10).type(TypeCompte.COURANT).build());
        compteRepository.deleteById(c.getId());
        assertThat(compteRepository.count()).isZero();
    }
}
