package fr.greta92.Domes.admin;

import fr.greta92.Domes.entities.Commande;
import fr.greta92.Domes.entities.StatutCommande;
import fr.greta92.Domes.services.CommandeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Suivi des commandes dans l'espace administrateur (rôle ADMIN, voir SecurityConfig) :
 *
 *  GET  /admin/commandes[?statut=PAYEE]   -> toutes les commandes, filtrables par statut
 *  POST /admin/commandes/{id}/statut      -> fait avancer une commande (payée -> expédiée -> livrée, ou annulée)
 */
@Controller
public class AdminCommandeController {

    private final CommandeService commandeService;

    public AdminCommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping("/admin/commandes")
    public String liste(@RequestParam(value = "statut", required = false) StatutCommande filtre, Model model) {
        model.addAttribute("commandes", commandeService.toutesLesCommandes(filtre));
        model.addAttribute("nombres", commandeService.nombreParStatut());
        model.addAttribute("statuts", StatutCommande.values());
        model.addAttribute("filtre", filtre);
        return "adminCommandes";
    }

    @PostMapping("/admin/commandes/{id}/statut")
    public String changerStatut(@PathVariable("id") int id,
                                @RequestParam("statut") StatutCommande nouveau,
                                @RequestParam(value = "filtre", required = false) StatutCommande filtre,
                                RedirectAttributes redirect) {
        try {
            Commande commande = commandeService.changerStatut(id, nouveau);
            String message = "Commande n° " + id + " : " + commande.getStatut().getLibelle().toLowerCase() + ".";
            if (nouveau == StatutCommande.ANNULEE) {
                message += " Ses animaux sont de nouveau en vente.";
            }
            redirect.addFlashAttribute("succes", message);
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirect.addFlashAttribute("erreur", e.getMessage());
        }
        // on revient sur le même onglet de filtre
        return "redirect:/admin/commandes" + (filtre != null ? "?statut=" + filtre.name() : "");
    }
}
