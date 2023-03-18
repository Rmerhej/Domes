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
         public String verifyIfUserIsClent(@ModelAttribute("clientc") Client client, BindingResult result, Model model) throws Exception {
            String dejaConnecte = (String) session.getAttribute("status");
            if(result.hasErrors()){
                return "seConnecter";
            }


            if("connected".equals(dejaConnecte)){
                model.addAttribute("dejaConnecte","vous êtes déjà connecté");
                model.addAttribute("connecte", true);
                return  "index";

            }

            else{
            System.out.println("ds verifyIfUserIsClent Connection Controller");
            boolean existe=clientService.clientExist(client);
            Client clientConnecteSession=clientService.findClient(client.getLogin());


            if(!existe){

                model.addAttribute("creezUnCompte","Veuillez créer un compte Client pour pouvoir se connecter");
                model.addAttribute("creez", true);
                return "connection";
                }
            if(existe){

                session.setAttribute("clientConnecteSession", clientConnecteSession);
                session.setAttribute("status","connected");
                String status=(String) session.getAttribute("status");
                model.addAttribute("message","Vous êtes Connecté");
                model.addAttribute("message", true);

                return "index";
            }

            model.addAttribute("message","Vous êtes Connecté");
                return "index";
        }}
    }
