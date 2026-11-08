    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <jsp:include page="header.jsp"></jsp:include>

    <div class="alert alert-success my-3">
      <h4 class="alert-heading">Merci pour votre commande !</h4>
      <p class="mb-0">Commande n° <strong><c:out value="${commande.id}"/></strong>
        passée le <c:out value="${commande.dateFormatee}"/>.
        Statut : <strong><c:out value="${commande.statut.libelle}"/></strong>.</p>
    </div>

    <table class="table align-middle">
      <thead>
        <tr>
          <th>Article</th>
          <th>Prix unitaire</th>
          <th>Quantité</th>
          <th>Sous-total</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="ligne" items="${commande.lignes}">
          <tr>
            <td><c:out value="${ligne.article.nom_article}"/></td>
            <td><fmt:formatNumber value="${ligne.prixUnitaire}" type="currency" currencySymbol="€" /></td>
            <td><c:out value="${ligne.quantite}"/></td>
            <td><fmt:formatNumber value="${ligne.sousTotal}" type="currency" currencySymbol="€" /></td>
          </tr>
        </c:forEach>
      </tbody>
      <tfoot>
        <tr>
          <th colspan="3" class="text-end">Total payé</th>
          <th><fmt:formatNumber value="${commande.montantTotal}" type="currency" currencySymbol="€" /></th>
        </tr>
      </tfoot>
    </table>

    <div class="d-flex gap-2 mb-4">
      <a href="/mesCommandes" class="btn btn-outline-primary">Mes commandes</a>
      <a href="/accueil" class="btn btn-primary">Retour à l'accueil</a>
    </div>

    <jsp:include page="footer.jsp"></jsp:include>
