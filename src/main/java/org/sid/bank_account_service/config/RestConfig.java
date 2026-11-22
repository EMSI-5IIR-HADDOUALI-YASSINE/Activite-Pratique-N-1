package org.sid.bank_account_service.config;

import org.sid.bank_account_service.entities.Compte;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // Par defaut Spring Data Rest n'expose pas l'id dans le JSON
        config.exposeIdsFor(Compte.class);
    }
}
