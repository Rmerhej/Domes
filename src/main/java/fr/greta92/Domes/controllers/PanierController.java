package fr.greta92.Domes.controllers;

import fr.greta92.Domes.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Panier en session (voir CartService).
 *
 *  GET  /panier                         -> affiche le panier
 *  POST /panier (idArticle, quantite)   -> ajoute un article (client connecté)
 *  GET  /suppDuPanier?id=..             -> retire un article
 */
@Controller
public class PanierController {

    private final CartService cartService;

    public PanierController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/panier")
    public String afficherPanier(HttpSession session, Model model) {
        model.addAttribute("items", cartService.getItems(session));
        model.addAttribute("montant", cartService.getTotal(session));
        return "panier";
    }

    @PostMapping("/panier")
    public String ajouterAuPanier(@RequestParam("idArticle") int idArticle,
                                  @RequestParam(value = "quantite", defaultValue = "1") int quantite,
                                  HttpSession session, Model model) {
        if (!"connected".equals(session.getAttribute("status"))) {
            model.addAttribute("nonConnecte", "Veuillez vous connecter si vous avez un compte, sinon créez-en un");
            model.addAttribute("notConnected", true);
            return "index";
        }
        cartService.addToCart(session, idArticle, quantite);
        // Redirection (pattern Post/Redirect/Get) : un F5 ne rajoute pas l'article une 2e fois
        return "redirect:/panier";
    }

    @GetMapping("/suppDuPanier")
    public String supprimerDuPanier(@RequestParam("id") int idArticle, HttpSession session) {
        cartService.removeItem(session, idArticle);
        return "redirect:/panier";
    }
}
