package fr.greta92.Domes;

import fr.greta92.Domes.entities.Commande;
import fr.greta92.Domes.repository.CommandeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/** Le parcours d'un client : visite, connexion, panier, commande, déconnexion. */
class ParcoursClientTest extends TestAvecDonnees {

    @Autowired
    private CommandeRepository commandeRepository;

    // ---------- Pages publiques ----------

    @Test
    void laccueilEstPublicEtMontreDesAnimaux() throws Exception {
        mvc.perform(get("/accueil"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("animaux", hasSize(1)));
    }

    @Test
    void lapageDuneEspeceMontreSesRacesEtSesAnimaux() throws Exception {
        mvc.perform(get("/tocateg").param("name", String.valueOf(chiens.getId_categorie())))
                .andExpect(status().isOk())
                .andExpect(view().name("produitAffichage"))
                .andExpect(model().attribute("produits", hasSize(1)))
                .andExpect(model().attribute("articles", hasSize(1)));
    }

    @Test
    void laRechercheTrouveUnAnimalParSonNom() throws Exception {
        mvc.perform(get("/search").param("search", "Fid"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("articles", hasSize(1)));
        mvc.perform(get("/search").param("search", "zzz"))
                .andExpect(model().attribute("articles", hasSize(0)));
    }

    @Test
    void laFicheDunAnimalSaffiche() throws Exception {
        mvc.perform(get("/animal/" + fidel.getId_article()))
                .andExpect(status().isOk())
                .andExpect(view().name("animal"))
                .andExpect(model().attributeExists("article"));
    }

    @Test
    void uneFicheInexistanteRenvoieAuCatalogue() throws Exception {
        mvc.perform(get("/animal/999999"))
                .andExpect(redirectedUrl("/catalogue"));
    }

    // ---------- Sécurité ----------

    @Test
    void unePageReserveeRenvoieVersLaConnexion() throws Exception {
        mvc.perform(get("/mesCommandes"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/seConnecter"));   // Spring Security 7 : adresse relative
    }

    @Test
    void unMauvaisMotDePasseEstRefuse() throws Exception {
        mvc.perform(formLogin("/connection").userParameter("login").passwordParam("mdp")
                        .user("roland").password("faux"))
                .andExpect(redirectedUrl("/seConnecter?erreur"));
    }

    @Test
    void unFormulaireSansJetonCsrfEstRefuse() throws Exception {
        mvc.perform(post("/panier").param("idArticle", String.valueOf(fidel.getId_article())))
                .andExpect(status().isForbidden());
    }

    // ---------- Panier et commande ----------

    @Test
    void parcoursCompletDuPanierALaCommande() throws Exception {
        MockHttpSession session = connecter("roland", "secret");

        // Ajout de 2 Fidel au panier
        mvc.perform(post("/panier").session(session).with(csrf())
                        .param("idArticle", String.valueOf(fidel.getId_article()))
                        .param("quantite", "2"))
                .andExpect(redirectedUrl("/panier"));
        assertThat(session.getAttribute("cartCount")).isEqualTo(2);

        // Récapitulatif
        mvc.perform(get("/achat").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("achat"))
                .andExpect(model().attribute("montant", 1000.0));

        // Paiement simulé
        MvcResult paiement = mvc.perform(post("/payer").session(session).with(csrf()).param("cgv", "ok"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertThat(paiement.getResponse().getRedirectedUrl()).startsWith("/commande/confirmation?id=");

        // La commande est en base, avec le bon total, et le panier est vidé
        List<Commande> commandes = commandeRepository.findAvecLignesParClient(roland.getId_client());
        assertThat(commandes).hasSize(1);
        assertThat(commandes.get(0).getMontantTotal()).isEqualTo(1000.0);
        assertThat(commandes.get(0).getLignes()).hasSize(1);
        assertThat(session.getAttribute("cart")).isNull();
    }

    @Test
    void lePaiementExigeLesConditionsGenerales() throws Exception {
        MockHttpSession session = connecter("roland", "secret");
        mvc.perform(post("/panier").session(session).with(csrf())
                .param("idArticle", String.valueOf(fidel.getId_article())));

        mvc.perform(post("/payer").session(session).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("paiement"))
                .andExpect(model().attributeExists("erreur"));
        assertThat(commandeRepository.findAvecLignesParClient(roland.getId_client())).isEmpty();
    }

    @Test
    void laDeconnexionDetruitLaSession() throws Exception {
        MockHttpSession session = connecter("roland", "secret");

        mvc.perform(post("/deconnection").session(session).with(csrf()))
                .andExpect(redirectedUrl("/accueil?deconnecte"));
        assertThat(session.isInvalid()).isTrue();
    }
}
