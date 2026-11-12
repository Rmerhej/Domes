package fr.greta92.Domes.services;

import fr.greta92.Domes.entities.Article;
import fr.greta92.Domes.entities.CartItem;
import fr.greta92.Domes.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Panier stocké dans la session HTTP : chaque visiteur a le sien.
 *
 * (L'ancien PanierService gardait une liste dans le service lui-même ;
 * comme un @Service est unique pour toute l'application, tous les
 * visiteurs partageaient le même panier.)
 *
 * Clé de la map = id de l'article. Après chaque modification on met à jour
 * l'attribut de session "cartCount", affiché dans le header.
 */
@Service
public class CartService {
    static final String CART = "cart";
    static final String CART_COUNT = "cartCount";

    private final ArticleRepository articleRepository;

    public CartService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /** @return false si l'article n'existe pas ou n'est plus en vente (déjà adopté) */
    public boolean addToCart(HttpSession session, int idArticle, int quantite) {
        Article article = articleRepository.findById(idArticle).orElse(null);
        if (article == null || !article.isDisponible()) {
            return false;
        }
        Map<Integer, CartItem> cart = getOrCreateCart(session);
        CartItem item = cart.get(idArticle);
        if (item == null) {
            cart.put(idArticle, new CartItem(article, quantite));
        } else {
            item.ajouterQuantite(quantite);
        }
        refreshCount(session, cart);
        return true;
    }

    public void removeItem(HttpSession session, int idArticle) {
        Map<Integer, CartItem> cart = getOrCreateCart(session);
        cart.remove(idArticle);
        refreshCount(session, cart);
    }

    public void clearCart(HttpSession session) {
        session.removeAttribute(CART);
        session.removeAttribute(CART_COUNT);
    }

    public Collection<CartItem> getItems(HttpSession session) {
        Map<Integer, CartItem> cart = readCart(session);
        return cart != null ? cart.values() : Collections.emptyList();
    }

    public int getCartItemCount(HttpSession session) {
        return getItems(session).stream().mapToInt(CartItem::getQuantite).sum();
    }

    public double getTotal(HttpSession session) {
        return getItems(session).stream().mapToDouble(CartItem::getSousTotal).sum();
    }

    @SuppressWarnings("unchecked")
    private Map<Integer, CartItem> readCart(HttpSession session) {
        return (Map<Integer, CartItem>) session.getAttribute(CART);
    }

    private Map<Integer, CartItem> getOrCreateCart(HttpSession session) {
        Map<Integer, CartItem> cart = readCart(session);
        if (cart == null) {
            cart = new LinkedHashMap<>();
            session.setAttribute(CART, cart);
        }
        return cart;
    }

    private void refreshCount(HttpSession session, Map<Integer, CartItem> cart) {
        session.setAttribute(CART_COUNT, cart.values().stream().mapToInt(CartItem::getQuantite).sum());
    }
}
