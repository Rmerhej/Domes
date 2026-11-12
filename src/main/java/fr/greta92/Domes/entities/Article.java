package fr.greta92.Domes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Entity
//@NamedQuery(name = "Article.searchArticleByName", query = "select a from Article a where a.nom_article like %:name% ")

public class Article {
    @Id
    // Compteur commun "hibernate_sequence", comme avant la migration (voir AlignementIdentifiants)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_article")
    @SequenceGenerator(name = "seq_article", sequenceName = "hibernate_sequence", allocationSize = 1)
    private int id_article;
    @NotEmpty(message = "ce champ ne doit pas être vide")
    private String nom_article;
    @NotNull(message = "ce champ ne doit pas être vide")
    private Double prix_unitaire_article;
    @NotEmpty(message = "ce champ ne doit pas être vide")
    private String image_article;
    // Texte de présentation affiché sur la fiche de l'animal (facultatif)
    @Column(columnDefinition = "TEXT")
    private String description;

    // En vente ou déjà adopté. Les animaux enregistrés avant l'ajout de cette colonne
    // ont la valeur null : on les considère comme disponibles (voir isDisponible).
    private Boolean disponible;

    @ManyToOne
    @JoinColumn(name = "id_produit", referencedColumnName = "id_produit")
    private Produit produit;
    @ManyToOne
    @JoinColumn(name = "id_categorie", referencedColumnName = "id_categorie")
    private Categorie categorie;

    public Article(){}
    public Article(int id_article, String nom_article, double prix_unitaire_article, String image_article) {
        this.id_article = id_article;
        this.nom_article = nom_article;
        this.prix_unitaire_article = prix_unitaire_article;
        this.image_article = image_article;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getNom_article() {
        return nom_article;
    }

    public void setNom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDisponible() {
        return disponible == null || disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrix_unitaire_article() {
        return prix_unitaire_article;
    }

    public void setPrix_unitaire_article(Double prix_unitaire_article) {
        this.prix_unitaire_article = prix_unitaire_article;
    }

    public String getImage_article() {
        return image_article;
    }

    public void setImage_article(String image_article) {
        this.image_article = image_article;
    }
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Produit getProduit() {
        return produit;
    }
    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id_article == article.id_article && nom_article.equals(article.nom_article) && prix_unitaire_article.equals(article.prix_unitaire_article) && image_article.equals(article.image_article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_article, nom_article, prix_unitaire_article, image_article);
    }

    @Override
    public String toString() {
        return "Article{" + "id_article=" + id_article + ", nom_article='" + nom_article + '\'' + ", prix_unitaire_article=" + prix_unitaire_article + ", image_article='" + image_article + '\'' + '}';
    }
}
