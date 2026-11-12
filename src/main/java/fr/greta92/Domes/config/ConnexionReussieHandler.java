package fr.greta92.Domes.config;

import fr.greta92.Domes.entities.Client;
import fr.greta92.Domes.repository.ClientRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Appelé par Spring Security quand la connexion réussit.
 *
 * Le reste de l'application (contrôleurs, JSP) lit le client connecté dans la
 * session : attributs "status" = "connected" et "clientConnecteSession".
 * On les remplit ici, pour garder ce fonctionnement.
 *
 * Ensuite : retour à la page demandée avant la connexion (ex. /achat),
 * ou à l'accueil avec un message.
 */
@Component
public class ConnexionReussieHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final ClientRepository clientRepository;

    public ConnexionReussieHandler(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        setDefaultTargetUrl("/accueil?connecte");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Client client = clientRepository.findByLogin(authentication.getName());
        HttpSession session = request.getSession();
        session.setAttribute("clientConnecteSession", client);
        session.setAttribute("status", "connected");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
