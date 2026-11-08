# Domes — animalerie en ligne

Projet de formation (GRETA 92) : boutique en ligne d'animaux de compagnie.

**Stack :** Java 11 · Spring Boot 2.7 · Spring MVC + JSP/JSTL · Spring Data JPA (Hibernate) · MySQL 8 · Bootstrap 5

## Fonctionnalités

- Catalogue par catégorie (chiens, chats, poissons, reptiles, oiseaux) et recherche par nom
- Création de compte, connexion (mots de passe hachés en BCrypt), modification du compte
- Panier en session (quantités, sous-totaux, total)
- Commande avec paiement **simulé** (aucune donnée bancaire), confirmation et historique « Mes commandes »
- Formulaire de contact par e-mail

## Lancer le projet

### Option 1 — Docker (le plus simple)

Prérequis : [Docker Desktop](https://www.docker.com/products/docker-desktop/).

```bash
cp .env.example .env        # Windows PowerShell : Copy-Item .env.example .env
# ouvrir .env et choisir un DB_PASSWORD
docker compose up --build
```

Ouvrir <http://localhost:8080>. La base MySQL est créée automatiquement et remplie
avec des animaux de démonstration (`src/main/resources/data.sql`).

Arrêter : `Ctrl+C`, puis `docker compose down`
(ajouter `-v` pour effacer aussi la base Docker).

### Option 2 — En local avec WAMP (ou tout MySQL)

Prérequis : JDK 11+, MySQL démarré, une base vide nommée `domesservice`.

1. Copier `src/main/resources/application-local.properties.example`
   en `application-local.properties` (même dossier, ignoré par Git) et y mettre
   l'utilisateur et le mot de passe MySQL.
2. Optionnel, pour charger les animaux de démo : ajouter `DB_INIT_MODE=always`
   dans ce même fichier.
3. Lancer `DomesApplication` depuis IntelliJ, ou en ligne de commande :
   ```bash
   ./mvnw spring-boot:run      # Windows : mvnw.cmd spring-boot:run
   ```
4. Ouvrir <http://localhost:8080>.

Les tables sont créées et mises à jour automatiquement par Hibernate
(`spring.jpa.hibernate.ddl-auto=update`).

## Configuration

Aucun mot de passe n'est écrit dans le code. `application.properties` lit ces variables
(depuis `application-local.properties`, le fichier `.env` de Docker ou l'environnement) :

| Variable        | Rôle                                   | Par défaut                                      |
|-----------------|----------------------------------------|-------------------------------------------------|
| `DB_URL`        | URL JDBC de la base                    | `jdbc:mysql://localhost:3306/domesservice`      |
| `DB_USERNAME`   | Utilisateur MySQL                      | `root`                                          |
| `DB_PASSWORD`   | Mot de passe MySQL                     | *(vide)*                                        |
| `DB_INIT_MODE`  | `always` pour charger `data.sql`       | `never`                                         |
| `MAIL_USERNAME` | Compte Gmail d'envoi                   | *(vide)*                                        |
| `MAIL_PASSWORD` | Mot de passe d'application Google      | *(vide)*                                        |

## Organisation du code

```
src/main/java/fr/greta92/Domes/
├── controllers/   pages et formulaires (Spring MVC)
├── services/      logique métier (panier, commandes, clients, e-mail)
├── repository/    accès aux données (Spring Data JPA)
├── entities/      classes persistées (Article, Client, Commande…)
└── config/        configuration MVC, vues JSP, encodeur de mots de passe
src/main/webapp/WEB-INF/jsp/   vues JSP
src/main/resources/static/     CSS et images
```
