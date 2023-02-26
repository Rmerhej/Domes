package fr.greta92.Domes.beans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class LignePanier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_ligne_panier;

    @NotNull
    private int quantite;
    @ManyToOne
    @JoinColumn(name = "id_panier")
    private Panier panier;
   @ManyToOne
   @JoinColumn(name = "id_article")
   private Article article;

   public LignePanier(){}
    public LignePanier(int id_ligne_panier,int quantite,Panier panier,Article article){
       this.id_ligne_panier=id_ligne_panier;
       this.quantite=quantite;
       this.panier=panier;
       this.article=article;
    }

    public int getId_ligne_panier() {
        return id_ligne_panier;
    }

    public void setId_ligne_panier(int id_ligne_panier) {
        this.id_ligne_panier = id_ligne_panier;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    @Override
    public String toString() {
        return "LignePanier{" +
                "id_ligne_panier=" + id_ligne_panier +
                ", quantite=" + quantite +
                ", panier=" + panier +
                ", article=" + article +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LignePanier that = (LignePanier) o;
        return id_ligne_panier == that.id_ligne_panier && quantite == that.quantite && panier.equals(that.panier) && article.equals(that.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_ligne_panier, quantite, panier, article);
    }
}
