    package fr.greta92.Domes.beans;

    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.Id;
    import javax.validation.constraints.NotEmpty;
    import java.util.Objects;

    @Entity
    public class Categorie {
        @Id
        @GeneratedValue
        private int id_categorie;
        @NotEmpty(message = "ce champ ne doit pas être vide")
        private  String nom_categorie;
        public Categorie(){}

        public Categorie(int id_categorie,String nom_categorie){
            this.id_categorie=id_categorie;
            this.nom_categorie=nom_categorie;
        }

        public int getId_categorie() {
            return id_categorie;
        }

        public void setId_categorie(int id_categorie) {
            this.id_categorie = id_categorie;
        }

        public String getNom_categorie() {
            return nom_categorie;
        }

        public void setNom_categorie(String nom_categorie) {
            this.nom_categorie = nom_categorie;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Categorie categorie = (Categorie) o;
            return id_categorie == categorie.id_categorie && nom_categorie.equals(categorie.nom_categorie);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id_categorie, nom_categorie);
        }

        @Override
        public String toString() {
            return "Categorie{" +
                    "id_categorie=" + id_categorie +
                    ", nom_categorie='" + nom_categorie + '\'' +
                    '}';
        }
    }
