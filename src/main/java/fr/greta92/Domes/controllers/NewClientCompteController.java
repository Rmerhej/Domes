    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.beans.Client;
    import fr.greta92.Domes.services.ClientService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;

    @Controller
    public class NewClientCompteController {
        @Autowired
       private ClientService clientService;
        @PostMapping("/newClientCompte")
        public String registerNewClient(@ModelAttribute("newclient") Client client, BindingResult result, Model model) throws Exception {
            if(result.hasErrors()){
                return "newCompte";
            }
            String s= clientService.createClient(client);
              if (s=="errorPage") {
                return "errorPage";
            }
            model.addAttribute("message","l'utilisateur est sauvgardé");
            return "newCompte";
        }
    }