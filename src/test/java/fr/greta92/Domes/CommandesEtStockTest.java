package fr.greta92.Domes;

import fr.greta92.Domes.entities.CartItem;
import fr.greta92.Domes.entities.Commande;
import fr.greta92.Domes.entities.StatutCommande;
import fr.greta92.Domes.services.CommandeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/** Disponibilité des animaux et suivi des commandes par l'administrateur. */
class CommandesEtStockTest extends TestAvecDonnees {

    @Autowired
    private CommandeService commandeService;

    private Commande commandeDeRoland() {
        return commandeService.passerCommande(roland.getId_client(), List.of(new CartItem(fidel, 1)));
    }

    // ---------- Disponibilité ----------

    @Test
    void unAnimalAcheteNestPlusEnVente() {
        assertThat(fidel.isDisponible()).isTrue();
        commandeDeRoland();
        assertThat(articleRepository.findById(fidel.getId_article()).orElseThrow().isDisponible()).isFalse();
    }

    @Test
    void unAnimalAdopteNePeutPasEtreAjouteAuPanier() throws Exception {
        commandeDeRoland();
        MockHttpSession session = connecter("roland", "secret");
        mvc.perform(post("/panier").session(session).with(csrf())
                        .param("idArticle", String.valueOf(fidel.getId_article())))
                .andExpect(redirectedUrl("/animal/" + fidel.getId_article()))
                .andExpect(flash().attributeExists("erreur"));
        assertThat(session.getAttribute("cart")).isNull();
    }

    @Test
    void onNePeutPasPayerUnAnimalAdopteEntreTemps() {
        // Roland a Fidel dans son panier, mais un autre client l'achète avant lui
        List<CartItem> panierDeRoland = List.of(new CartItem(fidel, 1));
        commandeDeRoland();
        assertThatThrownBy(() -> commandeService.passerCommande(roland.getId_client(), panierDeRoland))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("vient d'être adopté");
    }

    @Test
    void laccueilNeMontrePasLesAnimauxAdoptes() throws Exception {
        commandeDeRoland();
        mvc.perform(get("/accueil"))
                .andExpect(model().attribute("animaux", hasSize(0)));
    }

    // ---------- Commandes dans l'espace admin ----------

    @Test
    void ladminVoitToutesLesCommandes() throws Exception {
        commandeDeRoland();
        mvc.perform(get("/admin/commandes").session(connecter("admin", "admin123")))
                .andExpect(status().isOk())
                .andExpect(view().name("adminCommandes"))
                .andExpect(model().attribute("commandes", hasSize(1)));
    }

    @Test
    void leFiltreParStatutFonctionne() throws Exception {
        commandeDeRoland();
        MockHttpSession admin = connecter("admin", "admin123");
        mvc.perform(get("/admin/commandes").param("statut", "PAYEE").session(admin))
                .andExpect(model().attribute("commandes", hasSize(1)));
        mvc.perform(get("/admin/commandes").param("statut", "LIVREE").session(admin))
                .andExpect(model().attribute("commandes", hasSize(0)));
    }

    @Test
    void unClientNaPasAccesAuxCommandesDesAutres() throws Exception {
        mvc.perform(get("/admin/commandes").session(connecter("roland", "secret")))
                .andExpect(status().isForbidden());
    }

    @Test
    void ladminFaitAvancerUneCommande() throws Exception {
        Commande commande = commandeDeRoland();
        MockHttpSession admin = connecter("admin", "admin123");

        mvc.perform(post("/admin/commandes/" + commande.getId() + "/statut").session(admin).with(csrf())
                        .param("statut", "EXPEDIEE"))
                .andExpect(redirectedUrl("/admin/commandes"))
                .andExpect(flash().attributeExists("succes"));
        mvc.perform(post("/admin/commandes/" + commande.getId() + "/statut").session(admin).with(csrf())
                        .param("statut", "LIVREE"))
                .andExpect(flash().attributeExists("succes"));

        assertThat(statutEnBase(commande)).isEqualTo(StatutCommande.LIVREE);
    }

    @Test
    void unRetourEnArriereEstRefuse() throws Exception {
        Commande commande = commandeDeRoland();
        MockHttpSession admin = connecter("admin", "admin123");
        // payée -> livrée sans passer par « expédiée » : refusé
        mvc.perform(post("/admin/commandes/" + commande.getId() + "/statut").session(admin).with(csrf())
                        .param("statut", "LIVREE"))
                .andExpect(redirectedUrl("/admin/commandes"))
                .andExpect(flash().attributeExists("erreur"));
        assertThat(statutEnBase(commande)).isEqualTo(StatutCommande.PAYEE);
    }

    @Test
    void annulerUneCommandeRemetLesAnimauxEnVente() throws Exception {
        Commande commande = commandeDeRoland();
        mvc.perform(post("/admin/commandes/" + commande.getId() + "/statut")
                        .session(connecter("admin", "admin123")).with(csrf())
                        .param("statut", "ANNULEE").param("filtre", "PAYEE"))
                .andExpect(redirectedUrl("/admin/commandes?statut=PAYEE"));

        assertThat(statutEnBase(commande)).isEqualTo(StatutCommande.ANNULEE);
        assertThat(articleRepository.findById(fidel.getId_article()).orElseThrow().isDisponible()).isTrue();
    }

    @Test
    void ladminPeutRemettreUnAnimalEnVenteDepuisSaFiche() throws Exception {
        commandeDeRoland();
        mvc.perform(post("/admin/articles/enregistrer").session(connecter("admin", "admin123")).with(csrf())
                        .param("id", String.valueOf(fidel.getId_article()))
                        .param("nom", "Fidel").param("prix", "500")
                        .param("idProduit", String.valueOf(berger.getId_produit()))
                        .param("image", "images/German Shepherd.jpg")
                        .param("disponible", "true").param("_disponible", "on"))
                .andExpect(redirectedUrl("/admin"));
        assertThat(articleRepository.findById(fidel.getId_article()).orElseThrow().isDisponible()).isTrue();
    }

    @Test
    void decocherEnVenteRetireLanimalDeLaVente() throws Exception {
        // case décochée : le navigateur n'envoie que le champ caché "_disponible"
        mvc.perform(post("/admin/articles/enregistrer").session(connecter("admin", "admin123")).with(csrf())
                        .param("id", String.valueOf(fidel.getId_article()))
                        .param("nom", "Fidel").param("prix", "500")
                        .param("idProduit", String.valueOf(berger.getId_produit()))
                        .param("image", "images/German Shepherd.jpg")
                        .param("_disponible", "on"))
                .andExpect(redirectedUrl("/admin"));
        assertThat(articleRepository.findById(fidel.getId_article()).orElseThrow().isDisponible()).isFalse();
    }

    private StatutCommande statutEnBase(Commande commande) {
        entityManager.flush();
        entityManager.clear();   // relit vraiment la base, pas l'objet en mémoire
        return commandeService.toutesLesCommandes(null).stream()
                .filter(c -> c.getId() == commande.getId())
                .findFirst().orElseThrow().getStatut();
    }
}
