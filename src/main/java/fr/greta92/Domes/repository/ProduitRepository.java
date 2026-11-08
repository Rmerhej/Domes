    package fr.greta92.Domes.repository;


    import fr.greta92.Domes.entities.Produit;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    public interface ProduitRepository extends CrudRepository<Produit, Integer> {
      /**@Query(value = "SELECT * FROM produit WHERE produit.id_categorie = :name", nativeQuery = true)
        public Iterable<Produit> findById_categorie(@Param("name") String id_categorie);**/
      Produit findById(int id);
      List<Produit> findAll();

    }



