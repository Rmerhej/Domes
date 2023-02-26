    package fr.greta92.Domes.repository;


    import fr.greta92.Domes.beans.Produit;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import java.util.List;
    import java.util.Optional;
    @Repository
    public interface ProduitRepository extends CrudRepository<Produit, Integer> {
      /*  @Query("SELECT p FROM Produit p WHERE p.id_categorie=:name")
        List<Produit> searchArticleByName(@Param("name") String name);*/
      @Query("SELECT p FROM Produit p WHERE p.id_categorie= :name")
        public Iterable<Produit> findById_categorie(@Param("name") Integer id_categorie);

    }


