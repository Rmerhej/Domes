package fr.greta92.Domes.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Au démarrage : recale le compteur d'identifiants de Hibernate.
 *
 * Pourquoi ? Les entités en GenerationType.AUTO (Article, Client, Produit, Categorie)
 * reçoivent leur id d'un compteur commun, la table "hibernate_sequence" (sous MySQL).
 * Or des lignes ont été insérées avec un id choisi à la main (data.sql, phpMyAdmin) :
 * le compteur, lui, n'a pas avancé. Sans ce recalage, le premier animal créé dans
 * l'espace admin recevrait par exemple l'id 1, déjà pris, et l'enregistrement échouerait.
 *
 * On place donc le compteur au-delà du plus grand id existant. Sans effet si la table
 * n'existe pas (autre base de données, comme H2 dans les tests).
 */
@Component
public class AlignementIdentifiants implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(AlignementIdentifiants.class);

    // Tables dont l'id vient de hibernate_sequence, avec le nom de leur colonne id
    private static final String[][] TABLES = {
            {"article", "id_article"},
            {"client", "id_client"},
            {"produit", "id_produit"},
            {"categorie", "id_categorie"}
    };

    private final JdbcTemplate jdbc;

    public AlignementIdentifiants(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            long plusGrand = 0;
            for (String[] table : TABLES) {
                Long max = jdbc.queryForObject("SELECT COALESCE(MAX(" + table[1] + "), 0) FROM " + table[0], Long.class);
                if (max != null) {
                    plusGrand = Math.max(plusGrand, max);
                }
            }
            long prochain = plusGrand + 1;
            int lignes = jdbc.update("UPDATE hibernate_sequence SET next_val = ? WHERE next_val < ?", prochain, prochain);
            if (lignes > 0) {
                log.info("Compteur d'identifiants recalé : prochain id = {}", prochain);
            }
        } catch (DataAccessException e) {
            log.debug("Recalage des identifiants ignoré : {}", e.getMessage());
        }
    }
}
