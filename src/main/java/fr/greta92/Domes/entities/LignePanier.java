package fr.greta92.Domes.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

@Entity
public class LignePanier {
    @Id
    // Compteur commun "hibernate_sequence", comme avant la migration (voir AlignementIdentifiants)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lignepanier")
    @SequenceGenerator(name = "seq_lignepanier", sequenceName = "hibernate_sequence", allocationSize = 1)
    private int id_ligne;
    @ManyToOne
    private Article articleLignePanier;
    private int quantite;

    public LignePanier(){}

    public LignePanier(int id_ligne,Article articleLignePanier,int quantite){
        this.id_ligne=id_ligne;
        this.articleLignePanier=articleLignePanier;
        this.quantite=quantite;
    }
    public LignePanier(Article articleLignePanier,int quantite){
        this.articleLignePanier=articleLignePanier;
        this.quantite=quantite;
    }

    public Double prixLigne(Article article,int quantite){

      Double  prixdeCetteLigne = article.getPrix_unitaire_article()*quantite;
        return prixdeCetteLigne;
    }

    public int getId_ligne() {
        return id_ligne;
    }

    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }
    public Article getArticleLignePanier() {
        return articleLignePanier;
    }

    public void setArticleLignePanier(Article articleLignePanier) {
        this.articleLignePanier = articleLignePanier;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    @Override
    public String toString() {
        return "LignePanier{" +
                "id_ligne=" + id_ligne +
                ", articleLignePanier=" + articleLignePanier +
                ", quantite=" + quantite +
                '}';
    }

}
