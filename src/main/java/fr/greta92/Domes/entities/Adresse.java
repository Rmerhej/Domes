package fr.greta92.Domes.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.util.Objects;

/**
 * Adresse d'un client.
 *
 * C'est un objet "valeur" (@Embeddable) stocké dans la table "adresses"
 * via le @ElementCollection de Client : il n'a pas d'identifiant propre
 * et ne doit donc être ni une @Entity ni porter d'@Id.
 */
@Embeddable
public class Adresse {
    @Positive(message = "le numéro de voie doit être positif")
    private int numero_voie_adresse;
    @NotEmpty(message = "ce champ ne doit pas être vide")
    private String rue_adresse;
    @NotEmpty(message = "ce champ ne doit pas être vide")
    private String commune_adresse;
    @NotEmpty(message = "ce champ ne doit pas être vide")
    private String code_postal_adresse;
    @NotEmpty(message = "ce champ ne doit pas être vide")
    private String adresse_de_Livraison_adresse;

    public Adresse() {
    }

    public Adresse(String rue_adresse, String commune_adresse, String code_postal_adresse,
                   String adresse_de_Livraison_adresse, int numero_voie_adresse) {
        this.rue_adresse = rue_adresse;
        this.commune_adresse = commune_adresse;
        this.code_postal_adresse = code_postal_adresse;
        this.adresse_de_Livraison_adresse = adresse_de_Livraison_adresse;
        this.numero_voie_adresse = numero_voie_adresse;
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
        return numero_voie_adresse == adresse.numero_voie_adresse
                && Objects.equals(rue_adresse, adresse.rue_adresse)
                && Objects.equals(commune_adresse, adresse.commune_adresse)
                && Objects.equals(code_postal_adresse, adresse.code_postal_adresse)
                && Objects.equals(adresse_de_Livraison_adresse, adresse.adresse_de_Livraison_adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rue_adresse, commune_adresse, code_postal_adresse, adresse_de_Livraison_adresse, numero_voie_adresse);
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "numero_voie_adresse=" + numero_voie_adresse +
                ", rue_adresse='" + rue_adresse + '\'' +
                ", commune_adresse='" + commune_adresse + '\'' +
                ", code_postal_adresse='" + code_postal_adresse + '\'' +
                ", adresse_de_Livraison_adresse='" + adresse_de_Livraison_adresse + '\'' +
                '}';
    }
}
