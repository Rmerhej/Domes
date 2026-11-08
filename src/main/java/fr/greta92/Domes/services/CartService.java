package fr.greta92.Domes.services;

import fr.greta92.Domes.entities.CartItem;
import fr.greta92.Domes.entities.Produit;
import fr.greta92.Domes.repository.ProduitRepository;
import fr.greta92.Domes.repository.produitRepositoryImpl;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartService {
    private final ProduitRepository produitRepository=new produitRepositoryImpl();

    public void addToCart(HttpSession session, int produitId){
        @SuppressWarnings("uncheked")
                Map<Integer , CartItem> cart=(Map<Integer, CartItem>) session.getAttribute("cart");

        if(cart==null){
            cart=new LinkedHashMap<>();
            session.setAttribute("cart",cart);

        }

        if (cart.containsKey(produitId)) {
            cart.get(produitId).incrementQuantite();
        } else {
            Produit produit = produitRepository.findById(produitId);
            if (produit != null) {
                cart.put(produitId, new CartItem(produit));
            }
        }
    }

    public int getCartItemCount(HttpSession session) {
        @SuppressWarnings("unchecked")
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");

        if (cart == null) return 0;

        return cart.values().stream()
                .mapToInt(CartItem::getQuantite)
                .sum();
    }

    public Map<Integer, CartItem> getCart(HttpSession session) {
        @SuppressWarnings("unchecked")
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        return cart != null ? cart : Collections.emptyMap();
    }

    // Future methods: removeItem, updateQuantity, clearCart, getTotalAmount...
}


