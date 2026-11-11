package fr.greta92.Domes.config;

import fr.greta92.Domes.admin.StockagePhotos;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

/**
 * Configuration Spring MVC.
 *
 * On implémente WebMvcConfigurer (au lieu d'hériter de WebMvcConfigurationSupport) :
 * on ajoute nos réglages PAR-DESSUS la configuration automatique de Spring Boot,
 * au lieu de la remplacer entièrement.
 */
@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    private final StockagePhotos stockagePhotos;

    public ApplicationConfig(StockagePhotos stockagePhotos) {
        this.stockagePhotos = stockagePhotos;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // CSS et images servis depuis src/main/resources/static
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        // Photos envoyées depuis l'admin : lues dans le dossier d'upload, sur le disque
        // Le "/" final est indispensable : sans lui, Spring prend le dossier pour un fichier
        // (cas où le dossier n'existe pas encore au démarrage, avant le premier envoi)
        String dossier = stockagePhotos.getDossier().toUri().toString();
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(dossier.endsWith("/") ? dossier : dossier + "/");
    }

    // Les vues "index", "panier"... correspondent à /WEB-INF/jsp/index.jsp, /WEB-INF/jsp/panier.jsp...
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    // Site en français : prix "500,00 €" et dates au format français, quelle que soit
    // la langue du navigateur (Spring transmet cette langue aux balises <fmt:...> des JSP)
    @Bean
    public LocaleResolver localeResolver() {
        return new FixedLocaleResolver(Locale.FRANCE);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
