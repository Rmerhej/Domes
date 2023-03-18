    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.beans.Article;
    import fr.greta92.Domes.beans.LignePanier;
    import fr.greta92.Domes.beans.Panier;
    import fr.greta92.Domes.services.PanierService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    import javax.persistence.Query;
    import javax.servlet.http.HttpSession;
    import java.net.http.HttpRequest;
    import java.util.ArrayList;
    import java.util.List;

    @Controller
    public class PanierController {
        @Autowired
        PanierService panierService;
        @Autowired
        private HttpSession session;
        private HttpRequest request;

        @RequestMapping("/panier")
        public String ajouterAuPanier(@RequestParam("idArticle") int idArticle,@RequestParam("quantite") int quantite,Model model) throws Exception {

            String status = (String) session.getAttribute("status");
            if (status != "connected") {
                model.addAttribute("nonConnecte","Veuillez vous connecter si vous avez un compte,sinon creeez-en un");
                model.addAttribute("notConnected", true);
                return "index";
            }

            if (status == "connected") {
                List<LignePanier> ligneArticlesDunPanier=new ArrayList<>();
                Article articleAjoute = panierService.fetchArticle(idArticle);
                LignePanier ligneAjoutee =new LignePanier(articleAjoute,quantite);
                ligneArticlesDunPanier.add(ligneAjoutee);
                //List<Article> articles = panierService.getArticles();
                model.addAttribute("art", ligneArticlesDunPanier);
                Double montantTotal= panierService.montantPanier(ligneArticlesDunPanier);
                model.addAttribute("montant",montantTotal );
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
