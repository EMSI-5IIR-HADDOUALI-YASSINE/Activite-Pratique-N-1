package org.sid.bank_account_service.entities;

import org.springframework.data.rest.core.config.Projection;

/** Projection minimale : GET /datarest/comptes?projection=soldeSeul */
@Projection(name = "soldeSeul", types = Compte.class)
public interface CompteProjectionSolde {
    double getSolde();
}
