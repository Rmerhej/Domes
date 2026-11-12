# Domes — animalerie en ligne

[![CI](https://github.com/Rmerhej/Domes/actions/workflows/ci.yml/badge.svg)](https://github.com/Rmerhej/Domes/actions/workflows/ci.yml)

Projet de formation (GRETA 92) : boutique en ligne d'animaux de compagnie.

**Stack :** Java 21 · Spring Boot 4.1 · Spring MVC + JSP/JSTL · Spring Data JPA (Hibernate) · MySQL 8 · Bootstrap 5


https://github.com/user-attachments/assets/c3c06012-7022-458d-b27b-04f6f54fcd71

<img width="498" height="182" alt="PasserLaCommandeEtPayer" src="https://github.com/user-attachments/assets/21f09b5b-28e3-433f-bff9-3072ff254d45" />
<img width="462" height="863" alt="Capture d&#39;écran 2026-11-12 073043" src="https://github.com/user-attachments/assets/c4e5dcdf-b661-4a5f-b9f3-4b0622f9e41f" />
<img width="370" height="690" alt="catalogue" src="https://github.com/user-attachments/assets/c7dab74f-5c3a-40dc-81d8-041f8e623f9f" />
<img width="931" height="861" alt="Capture d&#39;écran 2026-11-12 073206" src="https://github.com/user-attachments/assets/7ef16749-5b9b-45cb-893e-9e1beec3cf76" />
<img width="506" height="192" alt="Commandes" src="https://github.com/user-attachments/assets/4dd1c8ce-b293-4351-b422-3093b52fabb5" />
<img width="745" height="689" alt="Page_Admin" src="https://github.com/user-attachments/assets/e825ca3c-f049-4e49-911e-340d7d820160" />
<img width="546" height="718" alt="Accueil" src="https://github.com/user-attachments/assets/a53f3f56-1826-4d5f-808a-ab08b2f2fe35" />








## Fonctionnalités

- Catalogue par catégorie (chiens, chats, poissons, reptiles, oiseaux) et recherche par nom
- Création de compte, connexion avec Spring Security (BCrypt, protection CSRF), modification du compte
- Panier en session (quantités, sous-totaux, total)
- Commande avec paiement **simulé** (aucune donnée bancaire), confirmation et historique « Mes commandes »
- Formulaire de contact par e-mail
- Fiche détaillée de chaque animal
- Espace administrateur (`/admin`) : ajout, modification et suppression des animaux, envoi de photos
- Suivi des commandes par l'administrateur (`/admin/commandes`) : filtre par statut, passage de « payée » à « expédiée » puis « livrée », ou annulation
- Disponibilité : un animal acheté est marqué « Adopté » et retiré de la vente ; il revient en vente si la commande est annulée

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

Prérequis : JDK 21+, MySQL démarré, une base vide nommée `domesservice`.

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

## Tests

```bash
./mvnw test      # Windows : mvnw.cmd test
```

Les tests utilisent une base H2 en mémoire : ni MySQL ni WAMP ne sont nécessaires.

Ils sont aussi lancés automatiquement par GitHub Actions à chaque pull request et à chaque
push sur `main`, avec une vérification de l'image Docker (`.github/workflows/ci.yml`).

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
| `CONTACT_EMAIL` | Destinataire du formulaire de contact  | `MAIL_USERNAME`                                 |
| `UPLOADS_DIR`   | Dossier des photos envoyées par l'admin | `uploads`                                      |
| `ADMIN_LOGINS`  | Logins administrateurs (accès `/admin`) | *(vide : aucun)*                               |

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
