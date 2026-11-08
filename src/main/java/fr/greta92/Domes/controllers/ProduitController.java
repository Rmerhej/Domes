    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.entities.Article;
    import fr.greta92.Domes.repository.SearchArticleRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestParam;

    import java.util.List;

    @Controller
    public class ProduitController {
        @Autowired
        SearchArticleRepository searchArticleRepository;

        @PostMapping("/articlesParRace")
        public String toarticles(@RequestParam("idCategorie") String name1,@RequestParam("nomProduit") String name2, Model model){
            List<Article>foundarticles=searchArticleRepository.findProduitByNom(name1,name2);
            model.addAttribute("foundarticles",foundarticles);
            return "articles";
        }
    }

