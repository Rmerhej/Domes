package fr.greta92.Domes.repository;

import fr.greta92.Domes.beans.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
    @Repository
    public interface SearchArticleRepository extends CrudRepository<Article, Integer> {
        @Query(value = "SELECT * FROM article  join produit on article.id_categorie= :id_categorie AND produit.nom_produit= :nom_produit",nativeQuery = true)
        List<Article> findProduitByNom(@Param("id_categorie") String id_categorie,@Param("nom_produit") String nom_produit);

    }

