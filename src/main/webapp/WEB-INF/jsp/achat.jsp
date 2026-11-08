    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <jsp:include page="header.jsp"></jsp:include>

    <h2 class="my-3">Récapitulatif de votre commande</h2>

    <table class="table align-middle">
        <thead>
           <tr>
              <th>Image</th>
              <th>Article</th>
              <th>Prix unitaire</th>
              <th>Quantité</th>
              <th>Sous-total</th>
            </tr>
         </thead>
        <tbody>
          <c:forEach var="item" items="${items}">
              <tr>
                 <td><img src="<c:url value="${item.article.image_article}" />" height="75" width="75" alt=""/></td>
                 <td><c:out value="${item.article.nom_article}" /></td>
                 <td><fmt:formatNumber value="${item.prixUnitaire}" type="currency" currencySymbol="€" /></td>
                 <td><c:out value="${item.quantite}" /></td>
                 <td><fmt:formatNumber value="${item.sousTotal}" type="currency" currencySymbol="€" /></td>
              </tr>
          </c:forEach>
        </tbody>
        <tfoot>
          <tr>
            <th colspan="4" class="text-end">Total à payer</th>
            <th><fmt:formatNumber value="${montant}" type="currency" currencySymbol="€" /></th>
          </tr>
        </tfoot>
   </table>

   <div class="d-flex gap-2 mb-4">
     <a href="/panier" class="btn btn-outline-secondary">Modifier le panier</a>
     <a href="/payer" class="btn btn-primary">Valider et payer</a>
   </div>

    <jsp:include page="footer.jsp"></jsp:include>
