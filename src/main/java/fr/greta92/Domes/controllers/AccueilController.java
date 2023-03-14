    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.beans.Client;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;

    @Controller
    public class AccueilController {
       @GetMapping("/accueil")
        public String goToAccueil(){
            System.out.println("page index.jsp");
            return "index";
        }

        @GetMapping("/seConnecter")
        public String connection(){
            System.out.println("page connection.jsp");
            return "connection";
        }

        @GetMapping("/mentionsLegales")
        public String goToMentionsLegales(){
            System.out.println("page mentionsLegales.jsp");
            return "mentionsLegales";
        }

        @GetMapping("/monCompte")
        public String goToMonCompte(){
            System.out.println("page monCompte.jsp");
            return "monCompte";
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
   
        @GetMapping("/achat")
        public String goToAchat(){
            System.out.println("page achat.jsp");
            return "achat";
        }
        @GetMapping("/payer")
        public String goTopaiement(){
            System.out.println("page paiement.jsp");
            return "paiement";
        }

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
