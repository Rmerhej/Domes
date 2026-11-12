package fr.greta92.Domes.admin;

import fr.greta92.Domes.entities.Article;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Données du formulaire d'un animal dans l'espace admin.
 *
 * On ne lie pas le formulaire directement à l'entité Article : on ne reçoit
 * que les champs modifiables, avec leurs propres règles de validation.
 */
public class ArticleForm {

    private Integer id;   // null pour un nouvel animal

    @NotBlank(message = "Indiquez le nom de l'animal.")
    @Size(max = 60, message = "60 caractères maximum.")
    private String nom;

    @NotNull(message = "Indiquez un prix.")
    @DecimalMin(value = "0.01", message = "Le prix doit être supérieur à 0.")
    @DecimalMax(value = "100000", message = "Le prix semble trop élevé.")
    private Double prix;

    // Facultatif ici : on peut aussi envoyer un fichier (contrôle dans AdminController)
    @Size(max = 255, message = "Chemin trop long.")
    private String image;

    @NotNull(message = "Choisissez une race.")
    private Integer idProduit;

    @Size(max = 2000, message = "2000 caractères maximum.")
    private String description;

    public static ArticleForm depuis(Article article) {
        ArticleForm form = new ArticleForm();
        form.id = article.getId_article();
        form.nom = article.getNom_article();
        form.prix = article.getPrix_unitaire_article();
        form.image = article.getImage_article();
        form.idProduit = article.getProduit() != null ? article.getProduit().getId_produit() : null;
        form.description = article.getDescription();
        return form;
    }

    public boolean isNouveau() {
        return id == null || id == 0;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public Integer getIdProduit() { return idProduit; }
    public void setIdProduit(Integer idProduit) { this.idProduit = idProduit; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
