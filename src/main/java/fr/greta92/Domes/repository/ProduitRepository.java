package fr.greta92.Domes.repository;

import fr.greta92.Domes.entities.Produit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends CrudRepository<Produit, Integer> {

    // Utilisée par SearchController (/tocateg) : les produits (races) d'une catégorie
    @Query(value = "SELECT * FROM produit WHERE produit.id_categorie = :name", nativeQuery = true)
    List<Produit> findById_categorie(@Param("name") String id_categorie);

    List<Produit> findAll();
}
