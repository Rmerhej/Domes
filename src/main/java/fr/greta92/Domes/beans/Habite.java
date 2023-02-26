    package fr.greta92.Domes.beans;

    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;
    import javax.validation.constraints.NotNull;
    import java.util.Objects;

    @Entity
    public class Habite {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id_client;
    @NotNull
        private int id_adresse;

        public int getId_client() {
            return id_client;
        }

        public void setId_client(int id_client) {
            this.id_client = id_client;
        }

        public int getId_adresse() {
            return id_adresse;
        }

        public void setId_adresse(int id_adresse) {
            this.id_adresse = id_adresse;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Habite habite = (Habite) o;
            return id_client == habite.id_client && id_adresse == habite.id_adresse;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id_client, id_adresse);
        }

        @Override
        public String toString() {
            return "Habite{" +
                    "id_client=" + id_client +
                    ", id_adresse=" + id_adresse +
                    '}';
        }
    }
