package fr.greta92.Domes;

import fr.greta92.Domes.entities.Article;
import fr.greta92.Domes.entities.CartItem;
import fr.greta92.Domes.services.CommandeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import fr.greta92.Domes.admin.StockagePhotos;

import java.nio.file.Files;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/** L'espace administrateur : accès réservé, ajout, validation, suppression. */
class AdminTest extends TestAvecDonnees {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private StockagePhotos stockagePhotos;

    @Test
    void unVisiteurEstRenvoyeVersLaConnexion() throws Exception {
        mvc.perform(get("/admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/seConnecter"));   // Spring Security 7 : adresse relative
    }

    @Test
    void unClientSansRoleAdminEstRefuse() throws Exception {
        MockHttpSession session = connecter("roland", "secret");
        mvc.perform(get("/admin").session(session))
                .andExpect(status().isForbidden());
    }

    @Test
    void ladministrateurVoitLaListe() throws Exception {
        MockHttpSession session = connecter("admin", "admin123");
        mvc.perform(get("/admin").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));
    }

    @Test
    void ladministrateurAjouteUnAnimal() throws Exception {
        MockHttpSession session = connecter("admin", "admin123");

        mvc.perform(post("/admin/articles/enregistrer").session(session).with(csrf())
                        .param("nom", "Rex")
                        .param("prix", "350")
                        .param("image", "images/Rottweiler.jpg")
                        .param("idProduit", String.valueOf(berger.getId_produit()))
                        .param("description", "Un chien calme."))
                .andExpect(redirectedUrl("/admin"))
                .andExpect(flash().attributeExists("succes"));

        List<Article> trouves = articleRepository.searchArticleByName("Rex");
        assertThat(trouves).hasSize(1);
        // l'espèce est déduite de la race
        assertThat(trouves.get(0).getCategorie().getId_categorie()).isEqualTo(chiens.getId_categorie());
        assertThat(trouves.get(0).getDescription()).isEqualTo("Un chien calme.");
    }

    @Test
    void unFormulaireIncompletEstReaffiche() throws Exception {
        MockHttpSession session = connecter("admin", "admin123");

        mvc.perform(post("/admin/articles/enregistrer").session(session).with(csrf())
                        .param("nom", "")
                        .param("prix", "-5")
                        .param("image", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("adminArticle"))
                .andExpect(model().attributeHasFieldErrors("article", "nom", "prix", "image", "idProduit"));
    }

    @Test
    void ladministrateurSupprimeUnAnimalSansCommande() throws Exception {
        MockHttpSession session = connecter("admin", "admin123");

        mvc.perform(post("/admin/articles/" + fidel.getId_article() + "/supprimer").session(session).with(csrf()))
                .andExpect(redirectedUrl("/admin"))
                .andExpect(flash().attributeExists("succes"));
        assertThat(articleRepository.findById(fidel.getId_article())).isEmpty();
    }

    @Test
    void unAnimalDejaCommandeNePeutPasEtreSupprime() throws Exception {
        commandeService.passerCommande(roland.getId_client(), List.of(new CartItem(fidel, 1)));
        MockHttpSession session = connecter("admin", "admin123");

        mvc.perform(post("/admin/articles/" + fidel.getId_article() + "/supprimer").session(session).with(csrf()))
                .andExpect(redirectedUrl("/admin"))
                .andExpect(flash().attributeExists("erreur"));
        assertThat(articleRepository.findById(fidel.getId_article())).isPresent();
    }

    // ---------- Envoi de photos ----------

    /** Début d'un vrai fichier JPEG : la signature FF D8 FF suffit à le reconnaître. */
    private static final byte[] JPEG = {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE0, 0, 0x10, 'J', 'F', 'I', 'F', 0, 1};

    @Test
    void ladministrateurEnvoieUnePhoto() throws Exception {
        MockHttpSession session = connecter("admin", "admin123");
        MockMultipartFile photo = new MockMultipartFile("fichierPhoto", "rex.jpg", "image/jpeg", JPEG);

        mvc.perform(multipart("/admin/articles/enregistrer").file(photo).session(session).with(csrf())
                        .param("nom", "Rex")
                        .param("prix", "350")
                        .param("image", "")
                        .param("idProduit", String.valueOf(berger.getId_produit())))
                .andExpect(redirectedUrl("/admin"));

        String chemin = articleRepository.searchArticleByName("Rex").get(0).getImage_article();
        // nom aléatoire, jamais celui envoyé par l'utilisateur
        assertThat(chemin).startsWith("uploads/").endsWith(".jpg").doesNotContain("rex");
        assertThat(Files.exists(stockagePhotos.getDossier().resolve(chemin.substring("uploads/".length())))).isTrue();
    }

    @Test
    void unFichierQuiNestPasUnePhotoEstRefuse() throws Exception {
        MockHttpSession session = connecter("admin", "admin123");
        // un texte déguisé en .jpg : refusé grâce au contrôle du contenu
        MockMultipartFile faux = new MockMultipartFile("fichierPhoto", "photo.jpg", "image/jpeg", "bonjour".getBytes());

        mvc.perform(multipart("/admin/articles/enregistrer").file(faux).session(session).with(csrf())
                        .param("nom", "Rex")
                        .param("prix", "350")
                        .param("idProduit", String.valueOf(berger.getId_produit())))
                .andExpect(status().isOk())
                .andExpect(view().name("adminArticle"))
                .andExpect(model().attributeHasFieldErrors("article", "image"));
        assertThat(articleRepository.searchArticleByName("Rex")).isEmpty();
    }

    @Test
    void sansPhotoNiFichierLeFormulaireEstReaffiche() throws Exception {
        MockHttpSession session = connecter("admin", "admin123");
        mvc.perform(post("/admin/articles/enregistrer").session(session).with(csrf())
                        .param("nom", "Rex")
                        .param("prix", "350")
                        .param("image", "")
                        .param("idProduit", String.valueOf(berger.getId_produit())))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("article", "image"));
    }
}
