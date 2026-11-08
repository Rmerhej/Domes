package fr.greta92.Domes.controllers;

import fr.greta92.Domes.entities.Article;
import fr.greta92.Domes.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

/** Fiche détaillée d'un animal : GET /animal/5 */
@Controller
public class AnimalController {

    private final ArticleRepository articleRepository;

    public AnimalController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/animal/{id}")
    public String fiche(@PathVariable("id") int id, Model model) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return "redirect:/catalogue";
        }
        model.addAttribute("article", article);

        // Jusqu'à 3 autres animaux de la même espèce, pour continuer la visite
        if (article.getCategorie() != null) {
            List<Article> autres = articleRepository.findByCategorie(article.getCategorie().getId_categorie())
                    .stream()
                    .filter(a -> a.getId_article() != id)
                    .limit(3)
                    .collect(Collectors.toList());
            model.addAttribute("autres", autres);
        }
        return "animal";
    }
}
