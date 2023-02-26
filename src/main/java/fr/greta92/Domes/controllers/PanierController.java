    package fr.greta92.Domes.controllers;
    /*
    import fr.greta92.Domes.beans.Article;
    import fr.greta92.Domes.beans.User;
    import fr.greta92.Domes.services.PanierService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;

    import javax.servlet.http.HttpSession;
    import javax.validation.Valid;
    import java.util.List;

    @Controller
    public class PanierController {
        User user=new User();
        @Autowired
        PanierService panierService;
        @Autowired
        private HttpSession session;

        @PostMapping("/panier")
        public String addToPanierService(@Valid @ModelAttribute("article") Article article, BindingResult result, Model model) throws Exception{
          List<Article> panierArticles= panierService.ajouterAuPanier(article);

            System.out.println("ds addToPanierService");
            System.out.println(session.getAttribute(user.getLogin()));
          return "panier";

        }
    }*/
