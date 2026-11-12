package fr.greta92.Domes.controllers;

import fr.greta92.Domes.entities.Client;
import fr.greta92.Domes.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

/**
 *  GET  /modifierCompte -> formulaire pré-rempli avec le client connecté
 *  POST /modification   -> enregistre les modifications
 *
 * L'id du client vient toujours de la session, jamais du formulaire :
 * un client ne peut modifier que son propre compte.
 */
@Controller
public class ModifierCompteController {

    private final ClientService clientService;

    public ModifierCompteController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/modifierCompte")
    public String modifierCompteClient(HttpSession session, Model model) {
        Client clientConnecte = clientConnecte(session);
        if (clientConnecte == null) {
            return "redirect:/seConnecter";
        }
        model.addAttribute("clientModifiant", clientConnecte);
        return "modificationCompte";
    }

    @PostMapping("/modification")
    public String modificationCompte(@ModelAttribute("clientModifiant") Client formulaire,
                                     @RequestParam(value = "nouveauMdp", required = false) String nouveauMdp,
                                     HttpSession session, Model model) {
        Client clientConnecte = clientConnecte(session);
        if (clientConnecte == null) {
            return "redirect:/seConnecter";
        }
        try {
            Client misAJour = clientService.updateClient(clientConnecte.getId_client(), formulaire, nouveauMdp);
            session.setAttribute("clientConnecteSession", misAJour);
            model.addAttribute("clientModifiant", misAJour);
            model.addAttribute("succes", "Vos coordonnées ont bien été modifiées.");
        } catch (IllegalArgumentException e) {
            // On réaffiche le formulaire avec la saisie de l'utilisateur
            model.addAttribute("erreur", e.getMessage());
        }
        return "modificationCompte";
    }

    private Client clientConnecte(HttpSession session) {
        if (!"connected".equals(session.getAttribute("status"))) {
            return null;
        }
        return (Client) session.getAttribute("clientConnecteSession");
    }
}
