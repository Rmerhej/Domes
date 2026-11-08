package fr.greta92.Domes.controllers;

import fr.greta92.Domes.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Formulaire "Nous contacter" (la page elle-même : GET /nousContacter dans AccueilController).
 *
 * Le message est envoyé à l'adresse CONTACT_EMAIL (par défaut : MAIL_USERNAME),
 * avec l'e-mail du visiteur en "Répondre à".
 */
@Controller
public class NousContacterController {

    private static final Logger log = LoggerFactory.getLogger(NousContacterController.class);

    private final EmailService emailService;
    private final String destinataire;

    public NousContacterController(EmailService emailService,
                                   @Value("${contact.destinataire:}") String destinataire) {
        this.emailService = emailService;
        this.destinataire = destinataire;
    }

    @PostMapping("/nousContacter")
    public String contact(@RequestParam(value = "nom", defaultValue = "") String nom,
                          @RequestParam(value = "prenom", defaultValue = "") String prenom,
                          @RequestParam(value = "mail", defaultValue = "") String mail,
                          @RequestParam(value = "msg", defaultValue = "") String msg,
                          Model model) {
        if (nom.isBlank() || mail.isBlank() || msg.isBlank()) {
            model.addAttribute("erreur", "Merci d'indiquer au moins votre nom, votre e-mail et votre message.");
            garderSaisie(model, nom, prenom, mail, msg);
            return "nousContacter";
        }
        if (destinataire.isBlank()) {
            log.warn("Formulaire de contact : aucune adresse configurée (CONTACT_EMAIL / MAIL_USERNAME)");
            model.addAttribute("erreur", "L'envoi de messages est momentanément indisponible. Merci de réessayer plus tard.");
            garderSaisie(model, nom, prenom, mail, msg);
            return "nousContacter";
        }
        try {
            String texte = "Nom : " + nom + "\nPrénom : " + prenom + "\nE-mail : " + mail + "\n\nMessage :\n" + msg;
            emailService.sendSimpleMessage(destinataire, "Message de Domes (" + nom + ")", texte, mail);
            model.addAttribute("succes", "Votre message a bien été envoyé. Nous vous répondrons rapidement.");
        } catch (MailException e) {
            log.error("Échec de l'envoi du formulaire de contact", e);
            model.addAttribute("erreur", "Votre message n'a pas pu être envoyé. Merci de réessayer plus tard.");
            garderSaisie(model, nom, prenom, mail, msg);
        }
        return "nousContacter";
    }

    /** Réaffiche ce que le visiteur avait tapé, pour qu'il n'ait pas à tout ressaisir. */
    private void garderSaisie(Model model, String nom, String prenom, String mail, String msg) {
        model.addAttribute("nom", nom);
        model.addAttribute("prenom", prenom);
        model.addAttribute("mail", mail);
        model.addAttribute("msg", msg);
    }
}
