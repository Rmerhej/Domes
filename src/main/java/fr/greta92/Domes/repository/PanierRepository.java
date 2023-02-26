    package fr.greta92.Domes.repository;

    import fr.greta92.Domes.beans.Article;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.data.repository.query.Param;

    import java.util.List;

    public interface PanierRepository extends CrudRepository<Article, Integer> {
        @Query(value = "SELECT * FROM article WHERE article.id_article = :id", nativeQuery = true)
        Article  findById(@Param("id") int id_article);
    }
