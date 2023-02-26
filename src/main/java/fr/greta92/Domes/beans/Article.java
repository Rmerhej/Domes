package fr.greta92.Domes.beans;

import fr.greta92.Domes.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.SessionRepository;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
//@NamedQuery(name = "Article.searchArticleByName", query = "select a from Article a where a.nom_article like %:name% ")

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_article;
    @NotEmpty(message = "ce champ ne doit pas être vide")
    private String nom_article;
    @NotEmpty(message = "ce champ ne doit pas être vide")
    private Double prix_unitaire_article;
    @NotEmpty(message = "ce champ ne doit pas être vide")
    private String image_article;
    @ManyToOne
    @JoinColumn(name = "id_produit", referencedColumnName = "id_produit")
    private Produit produit;

    public Article(){}
    public Article(int id_article, String nom_article, double prix_unitaire_article, String image_article) {
        this.id_article = id_article;
        this.nom_article = nom_article;
        this.prix_unitaire_article = prix_unitaire_article;
        this.image_article = image_article;
    }

    //@ManyToOne
    //@JoinColumn(name = "id_produit")
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
