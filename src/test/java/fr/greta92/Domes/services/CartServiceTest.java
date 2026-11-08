package fr.greta92.Domes.services;

import fr.greta92.Domes.entities.Article;
import fr.greta92.Domes.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test unitaire du panier : pas de Spring, pas de base de données.
 * Le dépôt d'articles est remplacé par un faux objet (Mockito).
 */
class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    void preparer() {
        ArticleRepository depot = mock(ArticleRepository.class);
        when(depot.findById(anyInt())).thenReturn(Optional.empty());
        when(depot.findById(1)).thenReturn(Optional.of(new Article(1, "Fidel", 500.0, "images/a.jpg")));
        when(depot.findById(2)).thenReturn(Optional.of(new Article(2, "Nemo", 12.5, "images/b.jpg")));
        cartService = new CartService(depot);
    }

    @Test
    void unPanierNeufEstVide() {
        MockHttpSession session = new MockHttpSession();
        assertThat(cartService.getItems(session)).isEmpty();
        assertThat(cartService.getTotal(session)).isZero();
    }

    @Test
    void lesQuantitesDuMemeArticleSadditionnent() {
        MockHttpSession session = new MockHttpSession();
        cartService.addToCart(session, 1, 1);
        cartService.addToCart(session, 1, 2);

        assertThat(cartService.getItems(session)).hasSize(1);
        assertThat(cartService.getCartItemCount(session)).isEqualTo(3);
        assertThat(session.getAttribute("cartCount")).isEqualTo(3);
    }

    @Test
    void leTotalTientCompteDesQuantites() {
        MockHttpSession session = new MockHttpSession();
        cartService.addToCart(session, 1, 2);   // 2 x 500
        cartService.addToCart(session, 2, 4);   // 4 x 12,50
        assertThat(cartService.getTotal(session)).isEqualTo(1050.0);
    }

    @Test
    void unArticleInconnuEstRefuse() {
        MockHttpSession session = new MockHttpSession();
        assertThat(cartService.addToCart(session, 99, 1)).isFalse();
        assertThat(cartService.getItems(session)).isEmpty();
    }

    @Test
    void retirerUnArticleMetAJourLeTotal() {
        MockHttpSession session = new MockHttpSession();
        cartService.addToCart(session, 1, 1);
        cartService.addToCart(session, 2, 2);
        cartService.removeItem(session, 1);
        assertThat(cartService.getTotal(session)).isEqualTo(25.0);
    }

    @Test
    void chaqueVisiteurASonProprePanier() {
        MockHttpSession visiteur1 = new MockHttpSession();
        MockHttpSession visiteur2 = new MockHttpSession();
        cartService.addToCart(visiteur1, 1, 1);
        assertThat(cartService.getItems(visiteur2)).isEmpty();
    }
}
