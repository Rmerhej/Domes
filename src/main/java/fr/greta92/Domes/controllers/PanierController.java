    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.beans.Article;
    import fr.greta92.Domes.repository.PanierRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import javax.servlet.http.HttpSession;
    import java.util.ArrayList;
    import java.util.List;

    import static org.springframework.web.bind.annotation.RequestMethod.POST;

    @Controller
    public class PanierController {
       /* @Autowired
        PanierService panierService;*/
        @Autowired
        private HttpSession session;
        @Autowired
        PanierRepository panierRepository;
        @RequestMapping("/panier")
        public String ajouterAuPanier(@RequestParam("id") String id_article, Model model) throws Exception {

            List<Article> articlesAjoutes=new ArrayList<Article>();
            System.out.println("ds ajoutPanier Controller");
            String status = (String) session.getAttribute("status");
            if (status != "connected") {
                return "connectionErrorPage";
            }

            if (status == "connected") {
                Article articleAjoute;
                articleAjoute =panierRepository.findById(Integer.parseInt(id_article));
                System.out.println(articleAjoute);
                articlesAjoutes.add(articleAjoute);
                model.addAttribute("articleAjoute", articleAjoute);

                return "panier";
            }
            return "panier";

        }
    }