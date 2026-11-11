package fr.greta92.Domes.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Espace administrateur (rôle ADMIN, voir SecurityConfig) :
 *
 *  GET  /admin                          -> liste des animaux
 *  GET  /admin/articles/nouveau         -> formulaire vide
 *  GET  /admin/articles/{id}            -> formulaire pré-rempli
 *  POST /admin/articles/enregistrer     -> création ou modification
 *  POST /admin/articles/{id}/supprimer  -> suppression
 */
@Controller
public class AdminController {

    private final AdminArticleService service;
    private final StockagePhotos stockagePhotos;

    public AdminController(AdminArticleService service, StockagePhotos stockagePhotos) {
        this.service = service;
        this.stockagePhotos = stockagePhotos;
    }

    @GetMapping("/admin")
    public String liste(Model model) {
        model.addAttribute("articles", service.tousLesArticles());
        return "admin";
    }

    @GetMapping("/admin/articles/nouveau")
    public String nouveau(Model model) {
        return formulaire(new ArticleForm(), model);
    }

    @GetMapping("/admin/articles/{id}")
    public String modifier(@PathVariable("id") int id, Model model, RedirectAttributes redirect) {
        return service.trouver(id)
                .map(article -> formulaire(ArticleForm.depuis(article), model))
                .orElseGet(() -> {
                    redirect.addFlashAttribute("erreur", "Cet animal n'existe plus.");
                    return "redirect:/admin";
                });
    }

    @PostMapping("/admin/articles/enregistrer")
    public String enregistrer(@Valid @ModelAttribute("article") ArticleForm form, BindingResult erreurs,
                              @RequestParam(value = "fichierPhoto", required = false) MultipartFile fichier,
                              Model model, RedirectAttributes redirect) {
        boolean fichierEnvoye = fichier != null && !fichier.isEmpty();
        if (!fichierEnvoye && (form.getImage() == null || form.getImage().isBlank())) {
            erreurs.rejectValue("image", "manquante", "Envoyez une photo ou choisissez-en une déjà disponible.");
        }
        if (erreurs.hasErrors()) {
            return formulaire(form, model);   // on réaffiche le formulaire avec les messages
        }

        // 1. La photo envoyée (si il y en a une) remplace le chemin choisi
        String photoEnvoyee = null;
        if (fichierEnvoye) {
            try {
                photoEnvoyee = stockagePhotos.enregistrer(fichier);
                form.setImage(photoEnvoyee);
            } catch (IllegalArgumentException e) {
                erreurs.rejectValue("image", "fichier", e.getMessage());
                return formulaire(form, model);
            } catch (IOException e) {
                model.addAttribute("erreur", "La photo n'a pas pu être enregistrée sur le serveur. Réessayez.");
                return formulaire(form, model);
            }
        }

        // 2. L'animal
        try {
            boolean nouveau = form.isNouveau();
            service.enregistrer(form);
            redirect.addFlashAttribute("succes", "« " + form.getNom().trim() + " » a bien été "
                    + (nouveau ? "ajouté." : "modifié."));
            return "redirect:/admin";
        } catch (IllegalArgumentException e) {
            stockagePhotos.supprimer(photoEnvoyee);   // pas de fichier orphelin
            model.addAttribute("erreur", e.getMessage());
            return formulaire(form, model);
        }
    }

    @PostMapping("/admin/articles/{id}/supprimer")
    public String supprimer(@PathVariable("id") int id, RedirectAttributes redirect) {
        try {
            service.supprimer(id);
            redirect.addFlashAttribute("succes", "L'animal a bien été supprimé.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirect.addFlashAttribute("erreur", e.getMessage());
        }
        return "redirect:/admin";
    }

    private String formulaire(ArticleForm form, Model model) {
        model.addAttribute("article", form);
        model.addAttribute("races", service.racesParEspece());
        model.addAttribute("photos", service.photosDisponibles());
        return "adminArticle";
    }
}
