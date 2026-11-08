-- ------------------------------------------------------------------
-- Données de démonstration de Domes
--
-- Exécuté au démarrage UNIQUEMENT si DB_INIT_MODE=always
-- (c'est le cas avec docker-compose ; pas par défaut avec WAMP).
--
-- INSERT IGNORE : si la ligne (même id) existe déjà, elle est ignorée.
-- Le script peut donc tourner à chaque démarrage sans créer de doublons
-- et sans écraser des données existantes.
--
-- Les id de catégories correspondent aux liens du menu (header.jsp) :
-- 1 Chiens, 2 Chats, 4 Poissons, 5 Reptiles, 6 Oiseaux.
-- ------------------------------------------------------------------

INSERT IGNORE INTO categorie (id_categorie, nom_categorie) VALUES
  (1, 'Chiens'),
  (2, 'Chats'),
  (4, 'Poissons'),
  (5, 'Reptiles'),
  (6, 'Oiseaux');

-- Produits = races / espèces
INSERT IGNORE INTO produit (id_produit, nom_produit, id_categorie) VALUES
  (1, 'Berger allemand', 1),
  (2, 'Rottweiler', 1),
  (3, 'Persan', 2),
  (4, 'Chat européen', 2),
  (5, 'Poisson rouge', 4),
  (6, 'Scalaire', 4),
  (7, 'Lézard vert', 5),
  (8, 'Canari', 6),
  (9, 'Gros-bec', 6);

-- Articles = animaux en vente (images dans src/main/resources/static/images)
INSERT IGNORE INTO article (id_article, nom_article, prix_unitaire_article, image_article, id_produit, id_categorie) VALUES
  (1, 'Fidel',  500.00, 'images/German Shepherd.jpg', 1, 1),
  (2, 'Rocky',  450.00, 'images/Rottweiler.jpg', 2, 1),
  (3, 'Mignon', 150.00, 'images/Persian.jpg', 3, 2),
  (4, 'Caramel', 90.00, 'images/the-red-or-white-cat-i-on-white-studio.jpg', 4, 2),
  (5, 'gaspar',  12.90, 'images/Goldfish.jpg', 5, 4),
  (6, 'Nemo',    25.00, 'images/Angelfish.jpg', 6, 4),
  (7, 'Kiwi',    80.00, 'images/green-lizard-on-branch-green-lizard-sunbathing-on-branch-green-lizard-climb-on-wood.jpg', 7, 5),
  (8, 'Titi',    45.00, 'images/Canari.jpg', 8, 6),
  (9, 'Pépite',  60.00, 'images/closeup-shot-of-male-hawfinch-sitting-on-branch.jpg', 9, 6);
