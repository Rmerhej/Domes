package fr.greta92.Domes.services;

import fr.greta92.Domes.entities.Client;
import fr.greta92.Domes.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Explique à Spring Security où trouver les utilisateurs : dans la table client.
 *
 * Spring compare ensuite le mot de passe saisi au hash BCrypt avec le
 * PasswordEncoder déclaré dans ApplicationConfig.
 *
 * Rôles :
 *  - tout client a le rôle CLIENT ;
 *  - les logins listés dans ADMIN_LOGINS (propriété domes.admin.logins,
 *    séparés par des virgules) ont en plus le rôle ADMIN.
 *
 * La comparaison ignore les majuscules et les espaces autour du login,
 * comme MySQL quand il retrouve le compte ("Roland " = "roland").
 */
@Service
public class ClientUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(ClientUserDetailsService.class);

    private final ClientRepository clientRepository;
    private final Set<String> loginsAdmin;

    public ClientUserDetailsService(ClientRepository clientRepository,
                                    @Value("${domes.admin.logins:}") String loginsAdmin) {
        this.clientRepository = clientRepository;
        this.loginsAdmin = Arrays.stream(loginsAdmin.split(","))
                .map(ClientUserDetailsService::normaliser)
                .filter(login -> !login.isEmpty())
                .collect(Collectors.toSet());
        // Visible dans les logs au démarrage (docker compose logs app)
        log.info("Administrateurs configurés (ADMIN_LOGINS) : {}",
                this.loginsAdmin.isEmpty() ? "aucun" : this.loginsAdmin);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client client = clientRepository.findByLogin(login);
        if (client == null || client.getMdp() == null) {
            throw new UsernameNotFoundException("Client inconnu");
        }
        boolean admin = loginsAdmin.contains(normaliser(client.getLogin()));
        log.info("Connexion de « {} » : rôle {}", client.getLogin(), admin ? "ADMIN + CLIENT" : "CLIENT");
        return User.withUsername(client.getLogin())
                .password(client.getMdp())
                .roles(admin ? new String[]{"CLIENT", "ADMIN"} : new String[]{"CLIENT"})
                .build();
    }

    private static String normaliser(String login) {
        return login == null ? "" : login.trim().toLowerCase(Locale.ROOT);
    }
}
