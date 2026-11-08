    package fr.greta92.Domes.repository;
    import fr.greta92.Domes.entities.Article;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.data.repository.query.Param;

    import java.util.List;


    public interface ArticleRepository extends CrudRepository<Article, Integer> {
        @Query("select a from Article a where a.nom_article like %:name% ")
        List<Article> searchArticleByName(@Param("name") String name);

        // Nombre de lignes de commande qui concernent cet animal (avant une suppression)
        @Query("select count(l) from LigneCommande l where l.article.id_article = :idArticle")
        long nombreDeLignesCommande(@Param("idArticle") int idArticle);

        // Tous les animaux d'une catégorie (chiens, chats...)
        @Query("select a from Article a where a.categorie.id_categorie = :idCategorie order by a.nom_article")
        List<Article> findByCategorie(@Param("idCategorie") int idCategorie);
    }
