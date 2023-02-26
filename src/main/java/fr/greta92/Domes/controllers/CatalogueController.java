    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.beans.Article;
    import fr.greta92.Domes.repository.ArticleRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;


    import java.util.ArrayList;
    import java.util.List;
    @Controller
    public class CatalogueController {
        @Autowired
        private ArticleRepository articleRepository;
        @RequestMapping(value = "/catalogue",method = RequestMethod.POST)
        public String catalogue( Model model ){
            System.out.println("CatalogueController");
            List<Article> articles=new ArrayList<Article>();
        articles = (List<Article>) articleRepository.findAll();
            articles.forEach((p) -> {
                System.out.println(p.getNom_article());
                System.out.println(p.getPrix_unitaire_article());
                System.out.println(p.getImage_article());

                    });
            model.addAttribute("articles",articles);
            return  "catalogue";
        }
    }
