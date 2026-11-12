package fr.greta92.Domes.entities;

import java.util.List;

/**
 * État d'une commande. Stocké en texte dans la base (@Enumerated STRING),
 * pour rester lisible dans phpMyAdmin et ne pas casser si on ajoute des valeurs.
 */
public enum StatutCommande {
    PAYEE("Payée"),
    EXPEDIEE("Expédiée"),
    LIVREE("Livrée"),
    ANNULEE("Annulée");

    private final String libelle;

    StatutCommande(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    /**
     * Les statuts vers lesquels l'administrateur peut faire passer une commande.
     * Une commande avance dans un seul sens ; livrée ou annulée, elle n'évolue plus.
     */
    public List<StatutCommande> getSuivants() {
        switch (this) {
            case PAYEE:    return List.of(EXPEDIEE, ANNULEE);
            case EXPEDIEE: return List.of(LIVREE, ANNULEE);
            default:       return List.of();
        }
    }

    public boolean peutPasserA(StatutCommande nouveau) {
        return getSuivants().contains(nouveau);
    }
}
