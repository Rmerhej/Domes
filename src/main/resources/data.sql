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

-- Descriptions affichées sur les fiches (seulement si elles sont encore vides,
-- pour ne jamais écraser un texte saisi dans l'espace administrateur)
UPDATE article SET description = 'Fidel est un jeune berger allemand vif et attentif. Il adore les longues promenades et apprend vite : idéal pour une famille sportive avec un jardin.' WHERE id_article = 1 AND description IS NULL;
UPDATE article SET description = 'Rocky est un chiot rottweiler calme et câlin. Il a besoin d''un maître présent et d''une éducation douce mais régulière.' WHERE id_article = 2 AND description IS NULL;
UPDATE article SET description = 'Mignon est un persan au long pelage soyeux. D''un tempérament paisible, il apprécie la vie en appartement et un brossage quotidien.' WHERE id_article = 3 AND description IS NULL;
UPDATE article SET description = 'Caramel, chat européen roux, est joueur et curieux. Il s''entend bien avec les enfants.' WHERE id_article = 4 AND description IS NULL;
UPDATE article SET description = 'Gaspar est un poisson rouge robuste, parfait pour débuter en aquariophilie. Prévoyez un aquarium filtré d''au moins 60 litres.' WHERE id_article = 5 AND description IS NULL;
UPDATE article SET description = 'Nemo est un scalaire élégant aux longues nageoires. Il vit en aquarium tropical planté, avec une eau autour de 26 °C.' WHERE id_article = 6 AND description IS NULL;
UPDATE article SET description = 'Kiwi est un lézard vert actif le jour. Il a besoin d''un terrarium chauffé avec une lampe UV et des branches pour grimper.' WHERE id_article = 7 AND description IS NULL;
UPDATE article SET description = 'Titi est un canari chanteur et facile à vivre. Une grande cage lumineuse et des graines variées feront son bonheur.' WHERE id_article = 8 AND description IS NULL;
UPDATE article SET description = 'Pépite est un gros-bec au bec puissant. Discret et sociable, il se plaît en volière avec d''autres oiseaux.' WHERE id_article = 9 AND description IS NULL;

-- ------------------------------------------------------------------
-- Nouveaux animaux (photos Pexels, voir la page Mentions légales).
-- Id à partir de 101 pour ne pas gêner les animaux créés dans l'espace admin.
-- ------------------------------------------------------------------

INSERT IGNORE INTO produit (id_produit, nom_produit, id_categorie) VALUES
  (101, 'Agame des jardins', 5),
  (102, 'Agame barbu', 5),
  (103, 'Chien croisé', 1),
  (104, 'Croisé husky', 1),
  (105, 'Montagne des Pyrénées', 1),
  (106, 'Poisson-cocher', 4),
  (107, 'Cichlidé perroquet', 4),
  (108, 'Rascasse volante', 4),
  (109, 'Chardonneret élégant', 6),
  (110, 'Grue royale', 6),
  (111, 'Ara bleu et jaune', 6);

INSERT IGNORE INTO article (id_article, nom_article, prix_unitaire_article, image_article, id_produit, id_categorie) VALUES
  (101, 'Sable',    95.00, 'images/agame-des-jardins.jpg', 101, 5),
  (102, 'Lucky',   350.00, 'images/chien-croise-tricolore.jpg', 103, 1),
  (103, 'Neige',   400.00, 'images/croise-husky.jpg', 104, 1),
  (104, 'Pogona',  180.00, 'images/agame-barbu.jpg', 102, 5),
  (105, 'Domino',  120.00, 'images/chaton-noir-et-blanc.jpg', 4, 2),
  (106, 'Grisou',  120.00, 'images/chaton-gris-et-blanc.jpg', 4, 2),
  (107, 'Moustache', 120.00, 'images/chaton-tigre-et-blanc.jpg', 4, 2),
  (108, 'Patou',   800.00, 'images/montagne-des-pyrenees.jpg', 105, 1),
  (109, 'Zèbre',    45.00, 'images/poisson-cocher.jpg', 106, 4),
  (110, 'Mandarine', 30.00, 'images/cichlide-perroquet.jpg', 107, 4),
  (111, 'Plume',    90.00, 'images/rascasse-volante.jpg', 108, 4),
  (112, 'Émeraude', 85.00, 'images/lezard-vert.jpg', 7, 5),
  (113, 'Pinson',   70.00, 'images/chardonneret-elegant.jpg', 109, 6),
  (114, 'Couronne', 2500.00, 'images/grue-royale.jpg', 110, 6),
  (115, 'Coco',    1800.00, 'images/ara-bleu-et-jaune.jpg', 111, 6);

UPDATE article SET description = 'Sable est un agame des jardins aux reflets dorés. Ce lézard arboricole aime grimper et se chauffer au soleil : prévoyez un terrarium haut, chauffé, avec lampe UV et branches.' WHERE id_article = 101 AND description IS NULL;
UPDATE article SET description = 'Lucky est un chien croisé tricolore au regard doux. Affectueux et curieux, il s''adapte à la vie de famille et adore les balades.' WHERE id_article = 102 AND description IS NULL;
UPDATE article SET description = 'Neige est un croisé husky au pelage clair. Énergique et sociable, il a besoin de beaucoup d''exercice et d''un maître sportif.' WHERE id_article = 103 AND description IS NULL;
UPDATE article SET description = 'Pogona est un agame barbu calme et facile à manipuler, idéal pour débuter avec les reptiles. Terrarium désertique chauffé, lampe UV, insectes et légumes frais.' WHERE id_article = 104 AND description IS NULL;
UPDATE article SET description = 'Domino est un chaton noir et blanc plein de malice. Il aime jouer, se blottir sur les genoux et découvrir chaque recoin de la maison.' WHERE id_article = 105 AND description IS NULL;
UPDATE article SET description = 'Grisou est un chaton gris et blanc très curieux. Toujours le premier à venir dire bonjour, il s''entend bien avec les enfants.' WHERE id_article = 106 AND description IS NULL;
UPDATE article SET description = 'Moustache est un chaton tigré et blanc au caractère doux. Un peu timide au début, il devient vite un vrai pot de colle.' WHERE id_article = 107 AND description IS NULL;
UPDATE article SET description = 'Patou est un montagne des Pyrénées, grand chien protecteur au cœur tendre. Calme à la maison, il a besoin d''espace, d''un jardin et d''un brossage régulier.' WHERE id_article = 108 AND description IS NULL;
UPDATE article SET description = 'Zèbre est un poisson-cocher aux rayures noires et blanches et à la longue nageoire dorsale. Il vit en aquarium marin récifal, en petit groupe.' WHERE id_article = 109 AND description IS NULL;
UPDATE article SET description = 'Mandarine est un cichlidé perroquet orange vif à la bouche ronde. Paisible et robuste, il vit en aquarium tropical d''au moins 200 litres.' WHERE id_article = 110 AND description IS NULL;
UPDATE article SET description = 'Plume est une rascasse volante aux nageoires en éventail. Superbe mais venimeuse : réservée aux aquariophiles expérimentés, en grand aquarium marin.' WHERE id_article = 111 AND description IS NULL;
UPDATE article SET description = 'Émeraude est un lézard vert aux couleurs éclatantes, né en captivité. Très actif le jour, il lui faut un grand terrarium chauffé avec lampe UV et cachettes.' WHERE id_article = 112 AND description IS NULL;
UPDATE article SET description = 'Pinson est un chardonneret élégant au masque rouge, né en élevage et bagué. Son chant mélodieux égaiera une volière spacieuse.' WHERE id_article = 113 AND description IS NULL;
UPDATE article SET description = 'Couronne est une grue royale à la huppe dorée. Oiseau majestueux, il demande un grand parc extérieur et un éleveur averti.' WHERE id_article = 114 AND description IS NULL;
UPDATE article SET description = 'Coco est un ara bleu et jaune, perroquet intelligent et bavard. Il vit très longtemps et a besoin d''une grande volière, de jouets et de beaucoup d''attention.' WHERE id_article = 115 AND description IS NULL;
