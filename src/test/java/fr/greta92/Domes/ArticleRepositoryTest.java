package fr.greta92.Domes;

import fr.greta92.Domes.beans.Article;
import fr.greta92.Domes.beans.Categorie;
import fr.greta92.Domes.beans.Produit;
import fr.greta92.Domes.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@ExtendWith({MockitoExtension.class})
public class ArticleRepositoryTest {
 @Mock
   private ArticleRepository articleRepository;
 @Mock
   private TestEntityManager testEntityManager;
   /* public ArticleRepositoryTest(@Param("articleRepository") ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }*/

    @Transactional
    @Test
    void testArticleByName_returnArticleDetail() {
        Categorie categorie=new Categorie(1,"chiens");
        Produit produit=new Produit(1,"bulldog",categorie);
        Article article = (Article)this.testEntityManager.persistAndFlush(new Article(1,"bull",200.5,"images/bully.jpg",produit));
        List<Article> articleList = this.articleRepository.searchArticleByName("bully");
        Article article1 = (Article)articleList.get(0);
      then(article1.getNom_article()).isEqualTo(article.getNom_article());
    }
}
