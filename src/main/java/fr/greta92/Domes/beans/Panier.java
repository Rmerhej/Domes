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
    public List<LignePanier>articlesDunPanier=new ArrayList<>();


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
    public List<LignePanier> getArticlesDunPanier() {
        return articlesDunPanier;
    }

    public void setArticlesDunPanier(List<LignePanier> articlesDunPanier) {
        this.articlesDunPanier = articlesDunPanier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Panier panier = (Panier) o;
        return id_panier == panier.id_panier && date_panier.equals(panier.date_panier) && client.equals(panier.client) && articlesDunPanier.equals(panier.articlesDunPanier);
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id_panier=" + id_panier +
                ", date_panier=" + date_panier +
                ", client=" + client +
                ", articlesDunPanier=" + articlesDunPanier +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_panier, date_panier, client, articlesDunPanier);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


}
