    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.beans.Client;
    import fr.greta92.Domes.beans.User;
    import org.hibernate.Session;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;

    import javax.servlet.http.HttpSession;

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
        /*
        @GetMapping("/modifierCompte")
        public String modifierC(){
            UserController userController=new UserController();
            if(userController.getConnected()){
            System.out.println("page modificationCompte.jsp");
            System.out.println(userController.getConnected());
            return "modificationCompte";}
            return "modifierCoordconnectedErrorPage";
        }*/
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
        @GetMapping("/panier")
        public String goToPanier(){
            System.out.println("page panier.jsp");
            return "panier";
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
        @GetMapping("/errorPage")
        public String goToErrorPage(){
            System.out.println("page errorPage.jsp");
            return "errorPage";
        }
        @GetMapping("/cgv")
        public String goToCgv(){
            System.out.println("page cgv.jsp");
            return "cgv";
        }
        @ModelAttribute("user")
        public User getDefaultUser(){
            return new User();
        }
        @ModelAttribute("newclient")
        public Client getDefaultClient(){
            return new Client();
        }
    }
