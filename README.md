# Micro-service de gestion de comptes bancaires

Activité pratique N°1 — EMSI [filière / année] — [Ton nom et prénom]

Micro-service Spring Boot qui permet de gérer des comptes bancaires. Il expose la même
fonctionnalité de trois façons : une API REST, une API Spring Data REST et une API GraphQL.

## Technologies

- Java 17+, Spring Boot 4.2
- Spring Web, Spring Data JPA, base H2 en mémoire, Lombok
- Spring Data REST (avec projections)
- Swagger / OpenAPI (springdoc)
- Spring GraphQL

## Structure du projet

| Dossier | Rôle |
|---------|------|
| `entities` | Entité JPA `Compte`, enum `TypeCompte`, projections Spring Data REST |
| `repositories` | `CompteRepository` (Spring Data JPA) |
| `dto` | `CompteRequestDTO` et `CompteResponseDTO` |
| `mappers` | `CompteMapper` (entité ↔ DTO) |
| `service` | Couche métier : `CompteService` et `CompteServiceImpl` |
| `web` | `CompteRestController` (REST) et `CompteGraphQLController` (GraphQL) |
| `config` | Configuration de Spring Data REST |

Un compte contient : un identifiant, un solde, une date de création et un type (`COURANT` ou `EPARGNE`).

## Lancer l'application

```
.\mvnw.cmd spring-boot:run
```

L'application démarre sur le port **8082** avec 3 comptes de démonstration.
Si Windows signale que `JAVA_HOME` n'est pas défini, définis-le pour la session en cours :
`$env:JAVA_HOME="C:\chemin\vers\ton\jdk"`.

## Lancer les tests

```
.\mvnw.cmd test
```

Les tests couvrent la couche DAO, l'API REST, Swagger, Spring Data REST et GraphQL.

## Les API

### API REST — `/api/comptes`

| Méthode | URL | Action |
|---------|-----|--------|
| GET | `/api/comptes` | Liste des comptes |
| GET | `/api/comptes/{id}` | Consulter un compte |
| POST | `/api/comptes` | Créer un compte |
| PUT | `/api/comptes/{id}` | Modifier un compte |
| DELETE | `/api/comptes/{id}` | Supprimer un compte |

Exemple de corps pour POST ou PUT :
```json
{ "solde": 3000, "type": "COURANT" }
```

### Documentation Swagger

http://localhost:8082/swagger-ui.html

### Spring Data REST — `/datarest`

- Liste : `GET /datarest/comptes`
- Projection : `GET /datarest/comptes?projection=mobile`
- Recherche par type : `GET /datarest/comptes/search/byType?t=EPARGNE`

### GraphQL

- Interface GraphiQL : http://localhost:8082/graphiql
- Endpoint : `POST /graphql`

```graphql
query { allComptes { id solde type dateCreation } }

mutation { saveCompte(compte: {solde: 4200, type: COURANT}) { id solde type } }
```

### Console H2

http://localhost:8082/h2-console — JDBC URL `jdbc:h2:mem:comptes-db`, utilisateur `sa`, mot de passe vide.

## Captures d'écran

<img width="1862" height="866" alt="image" src="https://github.com/user-attachments/assets/b509d3bf-8585-4bbe-95cb-58216f70dbbc" />

