    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.entities.Client;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.RequestParam;

    @Controller
    public class AccueilController {
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
            if (connecte != null) {
                model.addAttribute("message", "Vous êtes connecté");
                model.addAttribute("messageConnection", true);
            }
            if (deconnecte != null) {
                model.addAttribute("deconnecte", "Vous êtes déconnecté");
                model.addAttribute("dec", true);
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
            System.out.println("page mentionsLegales.jsp");
            return "mentionsLegales";
        }

        // monCompte.jsp n'existe pas : "Mon compte" = la page de modification
        @GetMapping("/monCompte")
        public String goToMonCompte(){
            return "redirect:/modifierCompte";
        }
        @GetMapping("/newCompte")
        public String goToNewCompte(){
            System.out.println("page newCompte.jsp");
            return "newCompte";
        }

        @GetMapping("/nousContacter")
        public String goToNousContacter(){
            System.out.println("page nousContacter.jsp");
            return "nousContacter";
        }
   
        // /achat et /payer : voir CommandeController

        @GetMapping("/cgv")
        public String goToCgv(){
            System.out.println("page cgv.jsp");
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
