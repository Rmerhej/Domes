package fr.greta92.Domes.controllers;

import fr.greta92.Domes.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/** Recherche d'un animal par son nom (barre de recherche du menu). */
@Controller
public class SearchController {

    private final ArticleRepository articleRepository;

    public SearchController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // GET : l'adresse de la recherche peut être partagée ou mise en favori ; POST gardé pour compatibilité
    @RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String search(@RequestParam(value = "search", defaultValue = "") String recherche, Model model) {
        String terme = recherche.trim();
        model.addAttribute("recherche", terme);
        model.addAttribute("articles", terme.isEmpty() ? java.util.List.of() : articleRepository.searchArticleByName(terme));
        return "articleRecherche";
    }
}
