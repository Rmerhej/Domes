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
      @Query(value = "SELECT * FROM produit WHERE produit.id_categorie = :name", nativeQuery = true)
        public Iterable<Produit> findById_categorie(@Param("name") String id_categorie);

    }



