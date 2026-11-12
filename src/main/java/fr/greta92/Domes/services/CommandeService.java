package fr.greta92.Domes.services;

import fr.greta92.Domes.entities.Article;
import fr.greta92.Domes.entities.CartItem;
import fr.greta92.Domes.entities.Client;
import fr.greta92.Domes.entities.Commande;
import fr.greta92.Domes.entities.StatutCommande;
import fr.greta92.Domes.repository.ArticleRepository;
import fr.greta92.Domes.repository.ClientRepository;
import fr.greta92.Domes.repository.CommandeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
            if (!article.isDisponible()) {
                // un autre client l'a acheté entre l'ajout au panier et le paiement
                throw new IllegalStateException("« " + article.getNom_article()
                        + " » vient d'être adopté. Retirez-le de votre panier pour continuer.");
            }
            Double prix = article.getPrix_unitaire_article();
            commande.ajouterLigne(article, item.getQuantite(), prix != null ? prix : 0.0);
        }
        // Les animaux achetés ne sont plus en vente (enregistré avec la commande,
        // dans la même transaction)
        commande.getLignes().forEach(ligne -> ligne.getArticle().setDisponible(false));
        return commandeRepository.save(commande);
    }

    // ---------- Espace administrateur ----------

    /** Toutes les commandes, les plus récentes d'abord ; filtre = null pour tout voir. */
    public List<Commande> toutesLesCommandes(StatutCommande filtre) {
        return commandeRepository.findAllAvecLignes().stream()
                .filter(c -> filtre == null || c.getStatut() == filtre)
                .collect(Collectors.toList());
    }

    /** Nombre de commandes par statut (pour les onglets de filtre). */
    public Map<StatutCommande, Long> nombreParStatut() {
        Map<StatutCommande, Long> nombres = new EnumMap<>(StatutCommande.class);
        for (StatutCommande s : StatutCommande.values()) {
            nombres.put(s, 0L);
        }
        commandeRepository.findAllAvecLignes().forEach(c -> nombres.merge(c.getStatut(), 1L, Long::sum));
        return nombres;
    }

    /**
     * Change le statut d'une commande. Une commande annulée remet ses animaux en vente.
     *
     * @throws IllegalArgumentException si la commande n'existe pas
     * @throws IllegalStateException    si ce changement de statut n'est pas permis
     */
    @Transactional
    public Commande changerStatut(int idCommande, StatutCommande nouveau) {
        Commande commande = commandeRepository.findAvecLignes(idCommande)
                .orElseThrow(() -> new IllegalArgumentException("La commande n° " + idCommande + " n'existe pas."));
        commande.changerStatut(nouveau);
        if (nouveau == StatutCommande.ANNULEE) {
            commande.getLignes().forEach(ligne -> ligne.getArticle().setDisponible(true));
        }
        return commandeRepository.save(commande);
    }

    /** Commandes du client, lignes comprises (prêtes à afficher dans une JSP). */
    public List<Commande> commandesDuClient(int idClient) {
        return commandeRepository.findAvecLignesParClient(idClient);
    }

    /** Une commande avec ses lignes, seulement si elle appartient bien à ce client. */
    public Optional<Commande> commandeDuClient(int idCommande, int idClient) {
        return commandeRepository.findAvecLignes(idCommande)
                .filter(c -> c.getClient().getId_client() == idClient);
    }
}
