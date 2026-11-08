package fr.greta92.Domes;

import fr.greta92.Domes.entities.Article;
import fr.greta92.Domes.entities.Categorie;
import fr.greta92.Domes.entities.Client;
import fr.greta92.Domes.entities.Produit;
import fr.greta92.Domes.repository.ArticleRepository;
import fr.greta92.Domes.repository.CategorieRepository;
import fr.greta92.Domes.repository.ClientRepository;
import fr.greta92.Domes.repository.ProduitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Base commune des tests d'intégration.
 *
 * - @SpringBootTest : démarre toute l'application (contrôleurs, sécurité, JPA)
 * - @AutoConfigureMockMvc : MockMvc envoie de fausses requêtes HTTP, sans serveur
 * - @ActiveProfiles("test") : base H2 en mémoire (src/test/resources/application-test.properties)
 * - @Transactional : chaque test est annulé à la fin, le suivant repart de zéro
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
abstract class TestAvecDonnees {

    @Autowired protected MockMvc mvc;
    @Autowired protected ClientRepository clientRepository;
    @Autowired protected ArticleRepository articleRepository;
    @Autowired protected ProduitRepository produitRepository;
    @Autowired protected CategorieRepository categorieRepository;
    @Autowired protected PasswordEncoder passwordEncoder;
    @Autowired protected EntityManager entityManager;

    protected Categorie chiens;
    protected Produit berger;
    protected Article fidel;
    protected Client roland;

    @BeforeEach
    void creerDonnees() {
        chiens = categorieRepository.save(new Categorie(0, "Chiens"));

        berger = new Produit(0, "Berger allemand");
        berger.setCategorie(chiens);
        berger = produitRepository.save(berger);

        fidel = new Article(0, "Fidel", 500.0, "images/German Shepherd.jpg");
        fidel.setProduit(berger);
        fidel.setCategorie(chiens);
        fidel = articleRepository.save(fidel);

        roland = clientRepository.save(new Client(0, "Merhej", "Roland", "0600000000",
                "roland@exemple.fr", "roland", passwordEncoder.encode("secret")));
        // "admin" est déclaré administrateur dans application-test.properties
        clientRepository.save(new Client(0, "Admin", "Domes", "0600000001",
                "admin@exemple.fr", "admin", passwordEncoder.encode("admin123")));

        // Envoie tout de suite les données à la base : certaines requêtes du projet
        // sont en SQL natif et ne verraient pas des données encore en mémoire
        entityManager.flush();
    }

    /** Se connecte par le vrai formulaire (POST /connection) et renvoie la session obtenue. */
    protected MockHttpSession connecter(String login, String motDePasse) throws Exception {
        MvcResult resultat = mvc.perform(formLogin("/connection")
                        .userParameter("login").passwordParam("mdp")
                        .user(login).password(motDePasse))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        return (MockHttpSession) resultat.getRequest().getSession(false);
    }
}
