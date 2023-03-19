    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.beans.Article;
    import fr.greta92.Domes.beans.Produit;
    import fr.greta92.Domes.repository.ArticleRepository;
    import fr.greta92.Domes.repository.ProduitRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import java.util.List;
    import java.util.Optional;

    @Controller
    public class SearchController {
       @Autowired
        private ArticleRepository articleRepository;

        @PostMapping("/search")
        public String search(@RequestParam("search") String name, Model model ){
            System.out.println("le controleur search");
            System.out.println("le parametre est = "+name);
            List<Article>articles;
            articles=articleRepository.searchArticleByName(name);
            System.out.println("liste "+articles.toString());
            System.out.println(articles.get(0).getNom_article());
            System.out.println(articles.get(0).getPrix_unitaire_article());
            model.addAttribute("articles",articles);
            return  "articleRecherche";
        }
        @Autowired
        private ProduitRepository produitRepository;
        @PostMapping("/tocateg")
        public String tochiens(@RequestParam("name") String name, Model model){
            System.out.println("le controleur SearchController tocateg");
            Iterable<Produit> produits=produitRepository.findById_categorie(name);
            System.out.println("liste "+produits.toString());
            model.addAttribute("produits",produits);
            return "produitAffichage";
        }

    }
