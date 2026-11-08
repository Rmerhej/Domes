package fr.greta92.Domes.repository;

import fr.greta92.Domes.entities.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
    @Repository
    public interface SearchArticleRepository extends CrudRepository<Article, Integer> {
        // Articles d'une race (produit) donnée, dans une catégorie donnée.
        // La jointure relie chaque article à SON produit (article.id_produit = produit.id_produit).
        @Query(value = "SELECT a.* FROM article a "
                     + "JOIN produit p ON a.id_produit = p.id_produit "
                     + "WHERE p.id_categorie = :id_categorie AND p.nom_produit = :nom_produit",
               nativeQuery = true)
        List<Article> findProduitByNom(@Param("id_categorie") String id_categorie,@Param("nom_produit") String nom_produit);

    }

