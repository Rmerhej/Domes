package fr.greta92.Domes.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Objects;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_commande;
    @NotEmpty(message = "ce champ ne doit pas être vide")
    private Date date_commande;
    @NotEmpty(message = "ce champ ne doit pas être vide")
    private int panier_id_panier_panier;

    public Commande(){}
    public Commande(int id_commande,Date date_commande,int panier_id_panier_panier ){
        this.id_commande=id_commande;
        this.date_commande=date_commande;
        this.panier_id_panier_panier= panier_id_panier_panier;

    }
    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public int getPanier_id_panier_panier() {
        return panier_id_panier_panier;
    }

    public void setPanier_id_panier_panier(int panier_id_panier_panier) {
        this.panier_id_panier_panier = panier_id_panier_panier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commande commande = (Commande) o;
        return id_commande == commande.id_commande && panier_id_panier_panier == commande.panier_id_panier_panier && date_commande.equals(commande.date_commande);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_commande, date_commande, panier_id_panier_panier);
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id_commande=" + id_commande +
                ", date_commande=" + date_commande +
                ", panier_id_panier_panier=" + panier_id_panier_panier +
                '}';
    }
}
