package org.sid.bank_account_service.repositories;

import org.sid.bank_account_service.entities.Compte;
import org.sid.bank_account_service.entities.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * DAO Spring Data. Annote avec @RepositoryRestResource pour etre aussi expose
 * par Spring Data Rest sous /datarest/comptes (etape 8).
 */
@RepositoryRestResource
public interface CompteRepository extends JpaRepository<Compte, Long> {

    /** GET /datarest/comptes/search/byType?t=EPARGNE */
    @RestResource(path = "/byType")
    List<Compte> findByType(@Param("t") TypeCompte type);
}
