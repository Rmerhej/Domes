package fr.greta92.Domes.admin;

import fr.greta92.Domes.entities.Article;
import fr.greta92.Domes.entities.Produit;
import fr.greta92.Domes.repository.ArticleRepository;
import fr.greta92.Domes.repository.ProduitRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;

/** Gestion des animaux (articles) par l'administrateur. */
@Service
public class AdminArticleService {

    private final ArticleRepository articleRepository;
    private final ProduitRepository produitRepository;
    private final StockagePhotos stockagePhotos;

    public AdminArticleService(ArticleRepository articleRepository, ProduitRepository produitRepository,
                               StockagePhotos stockagePhotos) {
        this.articleRepository = articleRepository;
        this.produitRepository = produitRepository;
        this.stockagePhotos = stockagePhotos;
    }

    public List<Article> tousLesArticles() {
        List<Article> articles = new ArrayList<>();
        articleRepository.findAll().forEach(articles::add);
        articles.sort(Comparator.comparing(Article::getNom_article, String.CASE_INSENSITIVE_ORDER));
        return articles;
    }

    public Optional<Article> trouver(int id) {
        return articleRepository.findById(id);
    }

    /** Les races, regroupées par espèce (pour la liste déroulante du formulaire). */
    public Map<String, List<Produit>> racesParEspece() {
        Map<String, List<Produit>> groupes = new LinkedHashMap<>();
        produitRepository.findAll().stream()
                .sorted(Comparator.comparing((Produit p) -> p.getCategorie() != null ? p.getCategorie().getNom_categorie() : "")
                        .thenComparing(Produit::getNom_produit))
                .forEach(p -> groupes
                        .computeIfAbsent(p.getCategorie() != null ? p.getCategorie().getNom_categorie() : "Sans espèce", k -> new ArrayList<>())
                        .add(p));
        return groupes;
    }

    /** Les photos disponibles dans static/images (chemins du type "images/Chat.jpg"). */
    public List<String> photosDisponibles() {
        TreeSet<String> photos = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        try {
            Resource[] fichiers = new PathMatchingResourcePatternResolver().getResources("classpath*:static/images/*");
            for (Resource fichier : fichiers) {
                String nom = fichier.getFilename();
                if (nom != null && nom.matches("(?i).+\\.(jpe?g|webp)$")) {
                    photos.add("images/" + nom);
                }
            }
        } catch (IOException | RuntimeException e) {
            // Liste impossible (ex. nom de fichier accentué sur un système sans UTF-8) :
            // le formulaire reste utilisable, l'administrateur saisit le chemin à la main
        }
        // Les photos envoyées depuis l'admin d'abord, puis celles fournies avec le site
        List<String> toutes = new ArrayList<>(stockagePhotos.photosEnvoyees());
        toutes.addAll(photos);
        return toutes;
    }

    /**
     * Crée ou met à jour un animal.
     *
     * @throws IllegalArgumentException si la race ou l'animal n'existe pas
     */
    @Transactional
    public Article enregistrer(ArticleForm form) {
        Produit race = produitRepository.findById(form.getIdProduit())
                .orElseThrow(() -> new IllegalArgumentException("Cette race n'existe pas."));

        Article article = form.isNouveau()
                ? new Article()
                : articleRepository.findById(form.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Cet animal n'existe plus."));

        String anciennePhoto = article.getImage_article();
        article.setNom_article(form.getNom().trim());
        article.setPrix_unitaire_article(form.getPrix());
        article.setImage_article(form.getImage().trim());
        article.setDescription(form.getDescription() == null || form.getDescription().isBlank()
                ? null : form.getDescription().trim());
        article.setDisponible(form.isDisponible());
        article.setProduit(race);
        article.setCategorie(race.getCategorie());   // l'espèce découle de la race
        Article enregistre = articleRepository.save(article);
        if (anciennePhoto != null && !anciennePhoto.equals(enregistre.getImage_article())) {
            supprimerPhotoSiInutilisee(anciennePhoto);
        }
        return enregistre;
    }

    /**
     * Supprime un animal.
     *
     * @throws IllegalStateException s'il figure dans des commandes (on garde l'historique)
     */
    @Transactional
    public void supprimer(int id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cet animal n'existe plus."));
        if (articleRepository.nombreDeLignesCommande(id) > 0) {
            // supprimer l'animal casserait l'historique des commandes des clients
            throw new IllegalStateException("« " + article.getNom_article()
                    + " » figure dans des commandes : il ne peut pas être supprimé.");
        }
        articleRepository.delete(article);
        supprimerPhotoSiInutilisee(article.getImage_article());
    }

    /** Une photo envoyée qu'aucun animal n'utilise plus est effacée du disque. */
    private void supprimerPhotoSiInutilisee(String chemin) {
        if (chemin != null && chemin.startsWith(StockagePhotos.PREFIXE)
                && articleRepository.nombreParPhoto(chemin) == 0) {
            stockagePhotos.supprimer(chemin);
        }
    }
}
