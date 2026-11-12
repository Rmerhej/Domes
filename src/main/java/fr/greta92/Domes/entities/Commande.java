package fr.greta92.Domes.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Une commande payée par un client.
 *
 * Elle est créée au paiement, à partir du panier en session,
 * et contient une LigneCommande par article.
 *
 * Table "commandes" (au pluriel) : l'ancienne table "commande" avait une
 * colonne obligatoire panier_id_panier_panier que Hibernate (ddl-auto=update)
 * ne supprime pas ; repartir sur une table neuve évite des erreurs à l'insertion.
 */
@Entity
@Table(name = "commandes")
public class Commande {

    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy 'à' HH:mm");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commande")
    private int id;

    @Column(name = "date_commande", nullable = false)
    private LocalDateTime dateCommande;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_client")
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false, length = 20)
    private StatutCommande statut;

    @Column(name = "montant_total", nullable = false)
    private double montantTotal;

    // cascade ALL : enregistrer la commande enregistre aussi ses lignes
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneCommande> lignes = new ArrayList<>();

    protected Commande() {
        // pour JPA
    }

    public Commande(Client client, LocalDateTime dateCommande) {
        this.client = client;
        this.dateCommande = dateCommande;
        this.statut = StatutCommande.PAYEE;
    }

    /** Ajoute une ligne et recalcule le total. */
    public void ajouterLigne(Article article, int quantite, double prixUnitaire) {
        LigneCommande ligne = new LigneCommande(this, article, quantite, prixUnitaire);
        lignes.add(ligne);
        montantTotal += ligne.getSousTotal();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    /** Pour les JSP : fmt:formatDate ne sait pas formater un LocalDateTime. */
    public String getDateFormatee() {
        return dateCommande != null ? dateCommande.format(FORMAT_DATE) : "";
    }

    public Client getClient() {
        return client;
    }

    public StatutCommande getStatut() {
        return statut;
    }

    /**
     * Fait avancer la commande (espace admin).
     *
     * @throws IllegalStateException si ce changement n'est pas permis (ex. livrée -> payée)
     */
    public void changerStatut(StatutCommande nouveau) {
        if (nouveau == null || !statut.peutPasserA(nouveau)) {
            throw new IllegalStateException("La commande n° " + id + " ne peut pas passer de « "
                    + statut.getLibelle() + " » à « " + (nouveau != null ? nouveau.getLibelle() : "?") + " ».");
        }
        this.statut = nouveau;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public List<LigneCommande> getLignes() {
        return lignes;
    }

    public int getNombreArticles() {
        return lignes.stream().mapToInt(LigneCommande::getQuantite).sum();
    }

    @Override
    public String toString() {
        // pas de client ni de lignes ici : évite les boucles infinies Commande <-> LigneCommande
        return "Commande{id=" + id + ", date=" + dateCommande + ", statut=" + statut + ", total=" + montantTotal + '}';
    }
}
