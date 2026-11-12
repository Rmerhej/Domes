    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.entities.Article;
    import fr.greta92.Domes.entities.Client;
    import fr.greta92.Domes.repository.ArticleRepository;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.RequestParam;

    @Controller
    public class AccueilController {

        private final ArticleRepository articleRepository;

        public AccueilController(ArticleRepository articleRepository) {
            this.articleRepository = articleRepository;
        }
        // http://localhost:8080/ -> page d'accueil
        @GetMapping("/")
        public String racine(){
            return "redirect:/accueil";
        }

        // ?connecte et ?deconnecte : ajoutés par Spring Security après connexion / déconnexion
        @GetMapping("/accueil")
        public String goToAccueil(@RequestParam(value = "connecte", required = false) String connecte,
                                  @RequestParam(value = "deconnecte", required = false) String deconnecte,
                                  Model model){
            // 4 animaux encore disponibles, tirés au hasard pour la vitrine de l'accueil
            List<Article> animaux = new ArrayList<>();
            articleRepository.findAll().forEach(a -> { if (a.isDisponible()) animaux.add(a); });
            Collections.shuffle(animaux);
            model.addAttribute("animaux", animaux.subList(0, Math.min(4, animaux.size())));
            if (connecte != null) {
                model.addAttribute("message", "Vous êtes connecté. Bonne visite !");
            }
            if (deconnecte != null) {
                model.addAttribute("deconnecte", "Vous êtes déconnecté. À bientôt !");
            }
            return "index";
        }

        // La connexion elle-même (POST /connection) est traitée par Spring Security (SecurityConfig)
        @GetMapping("/seConnecter")
        public String connection(){
            return "connection";
        }

        @GetMapping("/mentionsLegales")
        public String goToMentionsLegales(){
            return "mentionsLegales";
        }

        // monCompte.jsp n'existe pas : "Mon compte" = la page de modification
        @GetMapping("/monCompte")
        public String goToMonCompte(){
            return "redirect:/modifierCompte";
        }
        @GetMapping("/newCompte")
        public String goToNewCompte(){
            return "newCompte";
        }

        @GetMapping("/nousContacter")
        public String goToNousContacter(){
            return "nousContacter";
        }
   
        // /achat et /payer : voir CommandeController

        @GetMapping("/cgv")
        public String goToCgv(){
            return "cgv";
        }

        @ModelAttribute("newclient")
        public Client getDefaultClient(){
            return new Client();
        }

        @ModelAttribute("clientc")
        public Client getAClient(){
            return new Client();
        }
    }
