    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.beans.Article;
    import fr.greta92.Domes.repository.PanierRepository;
    import fr.greta92.Domes.services.PanierService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import javax.servlet.http.HttpSession;
    import java.util.List;



    @Controller
    public class PanierController {
        @Autowired
        PanierService panierService;
        @Autowired
        private HttpSession session;
        @Autowired
        PanierRepository panierRepository;
        @PostMapping("/panier")
        public String ajouterAuPanier(@RequestParam("idArticle") int idArticle, Model model) throws Exception {

            System.out.println("ds ajoutPanier Controller");
            String status = (String) session.getAttribute("status");
            if (status != "connected") {
                return "connectionErrorPage";
            }

            if (status == "connected") {
                Article articleAjoute = panierService.fetchArticle(idArticle);
                System.out.println(articleAjoute);
                panierService.addArticle(articleAjoute);
                List<Article> articles = panierService.getArticles();
                model.addAttribute("articles", articles);
                System.out.println("articlesAjoutes "+articles);
                model.addAttribute("art", articles);

                return "panier";
            }

            return "panier";
        }

        @GetMapping("/suppDuPanier")
        public String suppduPanier(@RequestParam("id") int id_Article,Model model){
            Article articleAsupprimer = panierService.fetchArticle(id_Article);
            Article articleSupprime=panierService.deleteArticle(articleAsupprimer);
            List<Article> articles = panierService.getArticles();
            model.addAttribute("articles", articles);
            System.out.println("articlesAjoutes "+articles);
            model.addAttribute("art", articles);
            return "panier";
        }
    }
