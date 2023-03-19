    package fr.greta92.Domes.beans;

    import org.springframework.beans.factory.annotation.Autowired;

    import javax.persistence.*;
    import javax.validation.constraints.NotEmpty;
    import javax.validation.constraints.NotNull;
    import java.util.Objects;

    @Entity
    public class Produit {
        @Id
        @GeneratedValue
        private int id_produit;
        @NotEmpty(message = "ce champ ne doit pas être vide")
        private String nom_produit;

        @ManyToOne
        @JoinColumn(name = "id_categorie", referencedColumnName = "id_categorie")
        private Categorie categorie;


        public Produit(){}
        public Produit(int id_produit,String nom_produit){
            this.id_produit=id_produit;;
            this.nom_produit=nom_produit;
        }

        public int getId_produit() {
            return id_produit;
        }

        public void setId_produit(int id_produit) {
            this.id_produit = id_produit;
        }

        public String getNom_produit() {
            return nom_produit;
        }

        public void setNom_produit(String nom_produit) {
            this.nom_produit = nom_produit;
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
            Produit produit = (Produit) o;
            return id_produit == produit.id_produit && nom_produit.equals(produit.nom_produit) && categorie.equals(produit.categorie);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id_produit, nom_produit, categorie);
        }

        @Override
        public String toString() {
            return "Produit{" +
                    "id_produit=" + id_produit +
                    ", nom_produit='" + nom_produit + '\'' +
                    ", categorie=" + categorie +
                    '}';
        }
    }
