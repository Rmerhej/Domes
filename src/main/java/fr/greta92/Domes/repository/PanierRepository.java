    package fr.greta92.Domes.repository;

    import fr.greta92.Domes.beans.Article;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.stereotype.Repository;

    import java.util.Optional;

    @Repository
    public interface PanierRepository extends JpaRepository<Article, Integer> {

      Article findById(int id_article);
    }
