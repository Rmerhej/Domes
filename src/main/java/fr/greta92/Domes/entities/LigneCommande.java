package fr.greta92.Domes.entities;

import javax.persistence.*;

/**
 * Une ligne de commande : un article, une quantité et le prix unitaire
 * AU MOMENT DE L'ACHAT.
 *
 * On copie le prix (au lieu de relire article.prix_unitaire_article) :
 * si le prix de l'article change demain, les anciennes commandes
 * doivent garder le prix réellement payé.
 */
@Entity
@Table(name = "ligne_commande")
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ligne_commande")
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_commande")
    private Commande commande;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_article")
    private Article article;

    @Column(nullable = false)
    private int quantite;

    @Column(name = "prix_unitaire", nullable = false)
    private double prixUnitaire;

    protected LigneCommande() {
        // pour JPA
    }

    LigneCommande(Commande commande, Article article, int quantite, double prixUnitaire) {
        if (quantite < 1) {
            throw new IllegalArgumentException("La quantité doit être au moins 1");
        }
        this.commande = commande;
        this.article = article;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }

    public int getId() {
        return id;
    }

    public Commande getCommande() {
        return commande;
    }

    public Article getArticle() {
        return article;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public double getSousTotal() {
        return prixUnitaire * quantite;
    }

    @Override
    public String toString() {
        return "LigneCommande{id=" + id + ", article=" + (article != null ? article.getNom_article() : null)
                + ", quantite=" + quantite + ", prixUnitaire=" + prixUnitaire + '}';
    }
}
