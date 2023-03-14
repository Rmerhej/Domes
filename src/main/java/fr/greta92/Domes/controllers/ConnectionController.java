    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.beans.Client;
    import fr.greta92.Domes.services.ClientService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    import javax.servlet.http.HttpSession;

    @Controller
    public class ConnectionController {
            @Autowired
            ClientService clientService;
            @Autowired
            private HttpSession session;

        @PostMapping("/connection")
         public String verifyIfUserIsClent( Client client, BindingResult result, Model model) throws Exception {
            String dejaConnecte = (String) session.getAttribute("status");
            if(result.hasErrors()){
                return "seConnecter";
            }

            if(dejaConnecte == "connected"){
                boolean alreadyConnected=true;
                model.addAttribute("dejaConnecte","vous êtes déjà connecté");
                return  "connection";

            }
            else{
            System.out.println("ds verifyIfUserIsClent Connection Controller");
            boolean existe=clientService.clientExist(client);
            Client clientConnecteSession=clientService.findClient(client.getLogin());

            System.out.println(clientConnecteSession);

            if(!existe){

                boolean dontExiste= true;
                model.addAttribute("dontExiste",dontExiste);
                model.addAttribute("creezUnCompte","Veuillez créer un compte Client pour pouvoir se connecter");
                return "connection";
                }
            if(existe){

                session.setAttribute("clientConnecteSession", clientConnecteSession);
                System.out.println("affichage client apres connection");
                session.setAttribute("status","connected");
                String status=(String) session.getAttribute("status");
                System.out.println(status);
                System.out.println(clientConnecteSession);
                model.addAttribute("message","Vous êtes Connecté");

                return "index";

            }

            model.addAttribute("message","Vous êtes Connecté");
                return "index";
        }}
    }
