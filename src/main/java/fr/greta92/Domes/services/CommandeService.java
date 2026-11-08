package fr.greta92.Domes.services;

import fr.greta92.Domes.entities.Article;
import fr.greta92.Domes.entities.CartItem;
import fr.greta92.Domes.entities.Client;
import fr.greta92.Domes.entities.Commande;
import fr.greta92.Domes.repository.ArticleRepository;
import fr.greta92.Domes.repository.ClientRepository;
import fr.greta92.Domes.repository.CommandeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;

    public CommandeService(CommandeRepository commandeRepository,
                           ClientRepository clientRepository,
                           ArticleRepository articleRepository) {
        this.commandeRepository = commandeRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }

    /**
     * Transforme le contenu du panier en commande payée.
     *
     * @Transactional : la commande et toutes ses lignes sont enregistrées
     * ensemble ; si une erreur survient au milieu, rien n'est enregistré.
     *
     * Les articles et leur prix sont relus en base : on ne fait pas
     * confiance au prix stocké dans la session.
     */
    @Transactional
    public Commande passerCommande(int idClient, Collection<CartItem> panier) {
        if (panier == null || panier.isEmpty()) {
            throw new IllegalStateException("Votre panier est vide");
        }
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new IllegalStateException("Client introuvable"));

        Commande commande = new Commande(client, LocalDateTime.now());
        for (CartItem item : panier) {
            int idArticle = item.getArticle().getId_article();
            Article article = articleRepository.findById(idArticle)
                    .orElseThrow(() -> new IllegalStateException("Un article de votre panier n'est plus disponible"));
            Double prix = article.getPrix_unitaire_article();
            commande.ajouterLigne(article, item.getQuantite(), prix != null ? prix : 0.0);
        }
        return commandeRepository.save(commande);
    }

    public List<Commande> commandesDuClient(int idClient) {
        return clientRepository.findById(idClient)
                .map(commandeRepository::findByClientOrderByDateCommandeDesc)
                .orElse(Collections.emptyList());
    }

    /** Une commande, seulement si elle appartient bien à ce client. */
    public Optional<Commande> commandeDuClient(int idCommande, int idClient) {
        return commandeRepository.findById(idCommande)
                .filter(c -> c.getClient().getId_client() == idClient);
    }
}
