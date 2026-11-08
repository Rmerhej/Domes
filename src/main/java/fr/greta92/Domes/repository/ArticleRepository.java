    package fr.greta92.Domes.repository;
    import fr.greta92.Domes.entities.Article;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.data.repository.query.Param;

    import java.util.List;


    public interface ArticleRepository extends CrudRepository<Article, Integer> {
        @Query("select a from Article a where a.nom_article like %:name% ")
        List<Article> searchArticleByName(@Param("name") String name);
    }
