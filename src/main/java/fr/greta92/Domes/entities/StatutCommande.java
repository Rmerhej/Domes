package fr.greta92.Domes.entities;

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
}
