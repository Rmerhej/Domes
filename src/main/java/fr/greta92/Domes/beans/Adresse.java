    package fr.greta92.Domes.beans;

    import javax.persistence.*;
    import javax.validation.constraints.NotEmpty;
    import java.util.Objects;

    @Entity
    @Embeddable
    public class Adresse {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        @NotEmpty(message = "ce champ ne doit pas être vide")
        private String rue_adresse;
        @NotEmpty(message = "ce champ ne doit pas être vide")
        private String commune_adresse;
        @NotEmpty(message = "ce champ ne doit pas être vide")
        private String code_postal_adresse;
        @NotEmpty(message = "ce champ ne doit pas être vide")
        private String adresse_de_Livraison_adresse;
        @NotEmpty(message = "ce champ ne doit pas être vide")
        private int numero_voie_adresse;

        public Adresse() {
        }
        public Adresse( int id,String rue_adresse,String commune_adresse,String code_postal_adresse,String adresse_de_Livraison_adresse,Integer numero_voie_adresse){
            this.id=id;
            this.rue_adresse=rue_adresse;
            this.commune_adresse=commune_adresse;
            this.code_postal_adresse=code_postal_adresse;
            this.adresse_de_Livraison_adresse= adresse_de_Livraison_adresse;
            this.numero_voie_adresse=numero_voie_adresse;

        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRue_adresse() {
            return rue_adresse;
        }

        public void setRue_adresse(String rue_adresse) {
            this.rue_adresse = rue_adresse;
        }

        public String getCommune_adresse() {
            return commune_adresse;
        }

        public void setCommune_adresse(String commune_adresse) {
            this.commune_adresse = commune_adresse;
        }

        public String getCode_postal_adresse() {
            return code_postal_adresse;
        }

        public void setCode_postal_adresse(String code_postal_adresse) {
            this.code_postal_adresse = code_postal_adresse;
        }

        public String getAdresse_de_Livraison_adresse() {
            return adresse_de_Livraison_adresse;
        }

        public void setAdresse_de_Livraison_adresse(String adresse_de_Livraison_adresse) {
            this.adresse_de_Livraison_adresse = adresse_de_Livraison_adresse;
        }

        public int getNumero_voie_adresse() {
            return numero_voie_adresse;
        }

        public void setNumero_voie_adresse(int numero_voie_adresse) {
            this.numero_voie_adresse = numero_voie_adresse;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Adresse adresse = (Adresse) o;
            return id == adresse.id && numero_voie_adresse == adresse.numero_voie_adresse && rue_adresse.equals(adresse.rue_adresse) && commune_adresse.equals(adresse.commune_adresse) && code_postal_adresse.equals(adresse.code_postal_adresse) && adresse_de_Livraison_adresse.equals(adresse.adresse_de_Livraison_adresse);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, rue_adresse, commune_adresse, code_postal_adresse, adresse_de_Livraison_adresse, numero_voie_adresse);
        }

        @Override
        public String toString() {
            return "Adresse{" +
                    "id_adresse=" + id +
                    ", rue_adresse='" + rue_adresse + '\'' +
                    ", commune_adresse='" + commune_adresse + '\'' +
                    ", code_postal_adresse='" + code_postal_adresse + '\'' +
                    ", adresse_de_Livraison_adresse='" + adresse_de_Livraison_adresse + '\'' +
                    ", numero_voie_adresse=" + numero_voie_adresse +
                    '}';
        }
    }
