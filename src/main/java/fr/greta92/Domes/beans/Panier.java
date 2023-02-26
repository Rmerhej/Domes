package fr.greta92.Domes.beans;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
   @NotNull
    private int id_commande;

   @ManyToOne
   @JoinColumn(name = "id_client")
        private Client client;



    @OneToMany(mappedBy = "panier",cascade = CascadeType.ALL)
    private List<LignePanier> lignePaniers =new ArrayList<>();

    public Panier(){}
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

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LignePanier> getLignePaniers() {
        return lignePaniers;
    }

    public void setLignePaniers(List<LignePanier> lignePaniers) {
        this.lignePaniers = lignePaniers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Panier panier = (Panier) o;
        return id_panier == panier.id_panier  && id_commande == panier.id_commande && date_panier.equals(panier.date_panier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_panier, date_panier, id_commande);
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id_panier=" + id_panier +
                ", date_panier=" + date_panier +
                ", id_commande=" + id_commande +
                '}';
    }
}
