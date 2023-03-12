package fr.greta92.Domes.beans;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_panier;
    @NotEmpty(message = "ce champ ne doit pas être vide")
    private Date date_panier;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @OneToMany
    public List<Article>articlesDunPanier=new ArrayList<>();


    public Panier(){}
    public Panier(int id_panier, Date date_panier){
        this.id_panier=id_panier;
        this.date_panier=date_panier;
    }


    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public Date getDate_panier() {
        return date_panier;
    }

    public void setDate_panier(Date date_panier) {
        this.date_panier = date_panier;
    }
    public List<Article> getArticlesDunPanier() {
        return articlesDunPanier;
    }

    public void setArticlesDunPanier(List<Article> articlesDunPanier) {
        this.articlesDunPanier = articlesDunPanier;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


}
