package fr.greta92.Domes.controllers;

import fr.greta92.Domes.entities.Article;
import fr.greta92.Domes.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class CatalogueController {

    private final ArticleRepository articleRepository;

    public CatalogueController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // GET pour un simple lien ; POST gardé pour compatibilité
    @RequestMapping(value = "/catalogue", method = {RequestMethod.GET, RequestMethod.POST})
    public String catalogue(Model model) {
        List<Article> articles = new ArrayList<>();
        articleRepository.findAll().forEach(articles::add);
        Collections.shuffle(articles);
        model.addAttribute("articles", articles);
        return "catalogue";
    }
}
