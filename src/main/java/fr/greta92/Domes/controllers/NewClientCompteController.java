    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.entities.Client;
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
        public String registerNewClient(@ModelAttribute("newclient") Client client, BindingResult result, Model model)
                throws Exception {
            if(result.hasErrors()){
                return "newCompte";
            }
            String s= clientService.createClient(client);
              if (s=="errorPage") {
                  boolean existance=true;
                  model.addAttribute("existingLogin",
                          "Ce login est déjà utilisé. Choisissez-en un autre.");
                       return "newCompte";
            }
            // Compte créé : on envoie vers la connexion, avec un message
              return "redirect:/seConnecter?inscrit";
        }
    }