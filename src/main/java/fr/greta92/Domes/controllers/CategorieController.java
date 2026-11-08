package fr.greta92.Domes.controllers;

import fr.greta92.Domes.entities.Categorie;
import fr.greta92.Domes.repository.ArticleRepository;
import fr.greta92.Domes.repository.CategorieRepository;
import fr.greta92.Domes.repository.ProduitRepository;
import fr.greta92.Domes.repository.SearchArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Page d'une espèce (catégorie) : ses races en filtres, et ses animaux.
 *
 *  GET /tocateg?name=1                       -> tous les chiens
 *  GET /tocateg?name=1&race=Berger allemand  -> seulement cette race
 */
@Controller
public class CategorieController {

    private final CategorieRepository categorieRepository;
    private final ProduitRepository produitRepository;
    private final ArticleRepository articleRepository;
    private final SearchArticleRepository searchArticleRepository;

    public CategorieController(CategorieRepository categorieRepository, ProduitRepository produitRepository,
                               ArticleRepository articleRepository, SearchArticleRepository searchArticleRepository) {
        this.categorieRepository = categorieRepository;
        this.produitRepository = produitRepository;
        this.articleRepository = articleRepository;
        this.searchArticleRepository = searchArticleRepository;
    }

    @RequestMapping(value = "/tocateg", method = {RequestMethod.GET, RequestMethod.POST})
    public String categorie(@RequestParam("name") String idCategorie,
                            @RequestParam(value = "race", required = false) String race,
                            Model model) {
        int id;
        try {
            id = Integer.parseInt(idCategorie);
        } catch (NumberFormatException e) {
            return "redirect:/catalogue";
        }
        Categorie categorie = categorieRepository.findById(id).orElse(null);
        if (categorie == null) {
            return "redirect:/catalogue";
        }
        boolean filtreRace = race != null && !race.isBlank();
        model.addAttribute("categorie", categorie);
        model.addAttribute("produits", produitRepository.findById_categorie(idCategorie));
        model.addAttribute("race", filtreRace ? race : null);
        model.addAttribute("articles", filtreRace
                ? searchArticleRepository.findProduitByNom(idCategorie, race)
                : articleRepository.findByCategorie(id));
        return "produitAffichage";
    }

    // Ancienne adresse (formulaire "Consulter" par race) : redirigée vers la page de l'espèce filtrée
    @RequestMapping(value = "/articlesParRace", method = {RequestMethod.GET, RequestMethod.POST})
    public String articlesParRace(@RequestParam("idCategorie") String idCategorie,
                                  @RequestParam("nomProduit") String nomProduit,
                                  RedirectAttributes redirect) {
        redirect.addAttribute("name", idCategorie);
        redirect.addAttribute("race", nomProduit);
        return "redirect:/tocateg";
    }
}
