package fr.greta92.Domes.controllers;

import fr.greta92.Domes.entities.Client;
import fr.greta92.Domes.entities.Commande;
import fr.greta92.Domes.services.CartService;
import fr.greta92.Domes.services.CommandeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Parcours d'achat :
 *
 *  GET  /achat                      -> récapitulatif du panier
 *  GET  /payer                      -> page de paiement (simulé)
 *  POST /payer                      -> enregistre la commande, vide le panier
 *  GET  /commande/confirmation?id=  -> confirmation de la commande
 *  GET  /mesCommandes               -> historique des commandes du client
 *
 * Toutes ces pages demandent d'être connecté.
 */
@Controller
public class CommandeController {

    private final CartService cartService;
    private final CommandeService commandeService;

    public CommandeController(CartService cartService, CommandeService commandeService) {
        this.cartService = cartService;
        this.commandeService = commandeService;
    }

    @GetMapping("/achat")
    public String recapitulatif(HttpSession session, Model model) {
        if (clientConnecte(session) == null) {
            return "redirect:/seConnecter";
        }
        if (cartService.getItems(session).isEmpty()) {
            return "redirect:/panier";
        }
        model.addAttribute("items", cartService.getItems(session));
        model.addAttribute("montant", cartService.getTotal(session));
        return "achat";
    }

    @GetMapping("/payer")
    public String pagePaiement(HttpSession session, Model model) {
        if (clientConnecte(session) == null) {
            return "redirect:/seConnecter";
        }
        if (cartService.getItems(session).isEmpty()) {
            return "redirect:/panier";
        }
        model.addAttribute("montant", cartService.getTotal(session));
        return "paiement";
    }

    @PostMapping("/payer")
    public String payer(@RequestParam(value = "cgv", required = false) String cgv,
                        HttpSession session, Model model, RedirectAttributes redirect) {
        Client client = clientConnecte(session);
        if (client == null) {
            return "redirect:/seConnecter";
        }
        if (cgv == null) {
            model.addAttribute("erreur", "Vous devez accepter les conditions générales de vente.");
            model.addAttribute("montant", cartService.getTotal(session));
            return "paiement";
        }
        try {
            Commande commande = commandeService.passerCommande(client.getId_client(), cartService.getItems(session));
            cartService.clearCart(session);
            // Post/Redirect/Get : un F5 sur la confirmation ne repasse pas la commande
            return "redirect:/commande/confirmation?id=" + commande.getId();
        } catch (IllegalStateException e) {
            redirect.addFlashAttribute("erreur", e.getMessage());
            return "redirect:/panier";
        }
    }

    @GetMapping("/commande/confirmation")
    public String confirmation(@RequestParam("id") int idCommande, HttpSession session, Model model) {
        Client client = clientConnecte(session);
        if (client == null) {
            return "redirect:/seConnecter";
        }
        return commandeService.commandeDuClient(idCommande, client.getId_client())
                .map(commande -> {
                    model.addAttribute("commande", commande);
                    return "confirmationCommande";
                })
                .orElse("redirect:/mesCommandes");
    }

    @GetMapping("/mesCommandes")
    public String mesCommandes(HttpSession session, Model model) {
        Client client = clientConnecte(session);
        if (client == null) {
            return "redirect:/seConnecter";
        }
        model.addAttribute("commandes", commandeService.commandesDuClient(client.getId_client()));
        return "mesCommandes";
    }

    private Client clientConnecte(HttpSession session) {
        if (!"connected".equals(session.getAttribute("status"))) {
            return null;
        }
        return (Client) session.getAttribute("clientConnecteSession");
    }
}
