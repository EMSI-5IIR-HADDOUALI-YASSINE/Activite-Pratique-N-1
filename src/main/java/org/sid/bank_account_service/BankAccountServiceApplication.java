package org.sid.bank_account_service;

import org.sid.bank_account_service.entities.Compte;
import org.sid.bank_account_service.entities.TypeCompte;
import org.sid.bank_account_service.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

	/** Jeu de donnees de demarrage (test de la couche DAO). */
	@Bean
	CommandLineRunner start(CompteRepository compteRepository) {
		return args -> {
			if (compteRepository.count() == 0) {
				compteRepository.save(Compte.builder().solde(5000).dateCreation(LocalDate.now()).type(TypeCompte.COURANT).build());
				compteRepository.save(Compte.builder().solde(12000).dateCreation(LocalDate.now()).type(TypeCompte.EPARGNE).build());
				compteRepository.save(Compte.builder().solde(800).dateCreation(LocalDate.now()).type(TypeCompte.COURANT).build());
			}
			compteRepository.findAll().forEach(System.out::println);
		};
	}
}
