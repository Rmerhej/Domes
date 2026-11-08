package fr.greta92.Domes.controllers;

import fr.greta92.Domes.services.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("add-to-cart")
public class AddToCartController  extends HttpServlet {
    private final CartService cartService = new CartService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("produitId"));

        cartService.addToCart(request.getSession(), produitId);

        // Redirect back to previous page
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer != null ? referer : "shop.jsp");
    }

}
