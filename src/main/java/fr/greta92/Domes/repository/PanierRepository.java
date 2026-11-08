    package fr.greta92.Domes.repository;

    import fr.greta92.Domes.entities.Article;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface PanierRepository extends JpaRepository<Article, Integer> {

      Article findById(int id_article);
    }
