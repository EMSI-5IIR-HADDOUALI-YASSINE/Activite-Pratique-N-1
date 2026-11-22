package org.sid.bank_account_service.entities;

import org.springframework.data.rest.core.config.Projection;

/**
 * Projection Spring Data Rest.
 * Usage : GET /datarest/comptes?projection=mobile  ou  GET /datarest/comptes/1?projection=mobile
 */
@Projection(name = "mobile", types = Compte.class)
public interface CompteProjection {
    Long getId();

    double getSolde();

    TypeCompte getType();
}
