package fr.greta92.Domes;

import fr.greta92.Domes.beans.Article;
import fr.greta92.Domes.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@ActiveProfiles("test")
public class ArticleRepositoryTest {
@Autowired
   private ArticleRepository articleRepository;
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
    @Test
    void testArticleByName_returnArticleDetail() {

         Article article=new Article(1, "gaspar", 320.20,"images/Goldfish.jpg");

        List<Article> articles= articleRepository.searchArticleByName("gaspar");

         then(articles.get(0).getNom_article()).isEqualTo(article.getNom_article());

    }
}
