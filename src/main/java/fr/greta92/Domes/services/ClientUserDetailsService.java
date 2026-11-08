package fr.greta92.Domes.services;

import fr.greta92.Domes.entities.Client;
import fr.greta92.Domes.repository.ClientRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Explique à Spring Security où trouver les utilisateurs : dans la table client.
 *
 * Spring compare ensuite le mot de passe saisi au hash BCrypt avec le
 * PasswordEncoder déclaré dans ApplicationConfig.
 */
@Service
public class ClientUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    public ClientUserDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client client = clientRepository.findByLogin(login);
        if (client == null || client.getMdp() == null) {
            throw new UsernameNotFoundException("Client inconnu");
        }
        return User.withUsername(client.getLogin())
                .password(client.getMdp())
                .roles("CLIENT")
                .build();
    }
}
