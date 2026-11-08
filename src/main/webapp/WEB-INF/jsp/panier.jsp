    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <jsp:include page="header.jsp"></jsp:include>

  <h2 class="my-3">Mon panier</h2>

  <c:if test="${not empty erreur}">
    <div class="alert alert-danger"><c:out value="${erreur}"/></div>
  </c:if>

  <c:choose>
    <c:when test="${empty items}">
      <p>Votre panier est vide.</p>
    </c:when>
    <c:otherwise>
      <table class="table align-middle">
           <thead>
             <tr>
               <th>Image</th>
               <th>Article</th>
               <th>Prix unitaire</th>
               <th>Quantité</th>
               <th>Sous-total</th>
               <th></th>
             </tr>
           </thead>
           <tbody>
             <c:forEach var="item" items="${items}">
               <tr>
                <td><img src="<c:url value="${item.article.image_article}" />" height="75" width="75" alt="" /></td>
                <td><c:out value="${item.article.nom_article}" /></td>
                <td><fmt:formatNumber value="${item.prixUnitaire}" type="currency" currencySymbol="€" /></td>
                <td><c:out value="${item.quantite}" /></td>
                <td><fmt:formatNumber value="${item.sousTotal}" type="currency" currencySymbol="€" /></td>
                <td><a href="/suppDuPanier?id=<c:out value="${item.article.id_article}" />" class="btn btn-outline-danger btn-sm">Supprimer</a></td>
               </tr>
             </c:forEach>
           </tbody>
           <tfoot>
             <tr>
               <th colspan="4" class="text-end">Total</th>
               <th><fmt:formatNumber value="${montant}" type="currency" currencySymbol="€" /></th>
               <th></th>
             </tr>
           </tfoot>
      </table>
      <a href="/achat" class="btn btn-success">Acheter</a>
    </c:otherwise>
  </c:choose>

    <jsp:include page="footer.jsp"></jsp:include>
