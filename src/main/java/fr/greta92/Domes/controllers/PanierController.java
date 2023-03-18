    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.beans.Article;
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

        @PostMapping("/panier")
        public String ajouterAuPanier(@RequestParam("idArticle") int idArticle, Model model) throws Exception {

            String status = (String) session.getAttribute("status");
            if (status != "connected") {
                boolean notConnected=true;
                model.addAttribute("nonConnecte","Veuillez vous connecter si vous avez un compte,sinon creeez-en un");
                model.addAttribute("notConnected", true);
                return "index";
            }

            if (status == "connected") {
                Article articleAjoute = panierService.fetchArticle(idArticle);
                panierService.addArticle(articleAjoute);
                List<Article> articles = panierService.getArticles();
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
            model.addAttribute("art", articles);
            return "panier";
        }
    }
