    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.beans.Client;
    import fr.greta92.Domes.repository.ClientRepository;
    import fr.greta92.Domes.services.ClientService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;

    import javax.servlet.http.HttpSession;

    @Controller
    public class ModifierCompteController{

        Client client;
        @Autowired
        ClientRepository clientRepository;
        @Autowired
        HttpSession session;
        @Autowired
        ClientService clientService;
        @GetMapping("/modifierCompte")
        public String modifierCompteClient(Model model) throws Exception{
            String connectedClient = (String) session.getAttribute("status");
            if(connectedClient=="connected") {
                Client clientModifiant = (Client) session.getAttribute("clientConnecteSession");
                model.addAttribute("clientModifiant", clientModifiant);
                System.out.println(clientModifiant);
                return "modificationCompte";
            }
            return "Vous n'êtes pas connecté";
        }
        @PostMapping("/modification")
        public String modificationComte(@ModelAttribute Client client) throws Exception {
            Client client0= (Client) session.getAttribute("clientConnecteSession");
            clientService.deleteClient(client0);
            Client updatedClient =clientService.updateClient(client);
            System.out.println("apres modif");
            System.out.println(updatedClient);
            return "index";
        }
    }

