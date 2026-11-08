package fr.greta92.Domes.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final String expediteur;

    public EmailService(JavaMailSender emailSender,
                        @Value("${spring.mail.username:}") String expediteur) {
        this.emailSender = emailSender;
        this.expediteur = expediteur;
    }

    /**
     * Envoie un e-mail texte.
     *
     * @param repondreA adresse utilisée quand on clique sur "Répondre" (peut être vide)
     * @throws MailException si l'envoi échoue (serveur injoignable, identifiants refusés...)
     */
    public void sendSimpleMessage(String to, String subject, String text, String repondreA) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        if (!expediteur.isBlank()) {
            message.setFrom(expediteur);
        }
        if (repondreA != null && !repondreA.isBlank()) {
            message.setReplyTo(repondreA);
        }
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
