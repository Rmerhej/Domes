package fr.greta92.Domes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Sécurité de l'application (Spring Security).
 *
 * - Connexion : formulaire de /seConnecter, envoyé en POST sur /connection
 *   (champs "login" et "mdp"), vérifié par ClientUserDetailsService + BCrypt.
 * - Déconnexion : POST /deconnection ; la session est détruite (panier compris).
 * - CSRF activé : chaque formulaire POST doit contenir le jeton _csrf
 *   (ajouté automatiquement par <form:form>, à la main dans les autres formulaires).
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final ConnexionReussieHandler connexionReussieHandler;

    public SecurityConfig(ConnexionReussieHandler connexionReussieHandler) {
        this.connexionReussieHandler = connexionReussieHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // pages réservées aux clients connectés
                .antMatchers(HttpMethod.POST, "/panier").authenticated()   // ajouter au panier
                .antMatchers("/achat", "/payer", "/commande/**", "/mesCommandes",
                             "/modifierCompte", "/modification").authenticated()
                // tout le reste est public : accueil, catalogue, recherche, inscription...
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/seConnecter")              // notre page JSP
                .loginProcessingUrl("/connection")      // URL du formulaire (traitée par Spring)
                .usernameParameter("login")
                .passwordParameter("mdp")
                .successHandler(connexionReussieHandler)
                .failureUrl("/seConnecter?erreur")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/deconnection")             // POST uniquement (protégé par CSRF)
                .logoutSuccessUrl("/accueil?deconnecte")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            );
        return http.build();
    }
}
