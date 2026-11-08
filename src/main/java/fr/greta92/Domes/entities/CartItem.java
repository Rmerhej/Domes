package fr.greta92.Domes.entities;

import java.io.Serializable;

/**
 * Une ligne du panier en session : un article + une quantité.
 *
 * Le panier porte sur des Article (et non des Produit) car c'est
 * l'Article qui a un prix (prix_unitaire_article) et une image ;
 * le Produit n'est que la "race" (ex. Berger allemand).
 *
 * Ce n'est pas une @Entity : le panier vit dans la session HTTP
 * tant que la commande n'est pas passée.
 */
public class CartItem implements Serializable {
    private final Article article;
    private int quantite;

    public CartItem(Article article, int quantite) {
        this.article = article;
        this.quantite = Math.max(1, quantite);
    }

    public Article getArticle() {
        return article;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        if (quantite > 0) {
            this.quantite = quantite;
        }
    }

    public void ajouterQuantite(int quantite) {
        if (quantite > 0) {
            this.quantite += quantite;
        }
    }

    public double getPrixUnitaire() {
        Double prix = article.getPrix_unitaire_article();
        return prix != null ? prix : 0.0;
    }

    public double getSousTotal() {
        return getPrixUnitaire() * quantite;
    }
}
