package fr.greta92.Domes.services;

import fr.greta92.Domes.entities.Client;
import fr.greta92.Domes.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
 */
@Service
public class ClientUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final Set<String> loginsAdmin;

    public ClientUserDetailsService(ClientRepository clientRepository,
                                    @Value("${domes.admin.logins:}") String loginsAdmin) {
        this.clientRepository = clientRepository;
        this.loginsAdmin = Arrays.stream(loginsAdmin.split(","))
                .map(String::trim)
                .filter(login -> !login.isEmpty())
                .collect(Collectors.toSet());
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client client = clientRepository.findByLogin(login);
        if (client == null || client.getMdp() == null) {
            throw new UsernameNotFoundException("Client inconnu");
        }
        String[] roles = loginsAdmin.contains(client.getLogin())
                ? new String[]{"CLIENT", "ADMIN"}
                : new String[]{"CLIENT"};
        return User.withUsername(client.getLogin())
                .password(client.getMdp())
                .roles(roles)
                .build();
    }
}
