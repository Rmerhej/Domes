<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"><jsp:param name="titre" value="Récapitulatif"/></jsp:include>

<div class="container section">
  <h1 class="titre-page">Récapitulatif de votre commande</h1>
  <p class="chapeau">Vérifiez votre commande avant de passer au paiement.</p>

  <div class="tableau table-responsive mt-3">
    <table class="table align-middle">
      <thead>
        <tr>
          <th scope="col">Animal</th>
          <th scope="col">Prix unitaire</th>
          <th scope="col">Quantité</th>
          <th scope="col" class="text-end">Sous-total</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="item" items="${items}">
          <tr>
            <td>
              <div class="d-flex align-items-center gap-3">
                <img src="<c:url value="/${item.article.image_article}"/>" alt="">
                <strong><c:out value="${item.article.nom_article}"/></strong>
              </div>
            </td>
            <td><fmt:formatNumber value="${item.prixUnitaire}" type="currency" currencyCode="EUR"/></td>
            <td><c:out value="${item.quantite}"/></td>
            <td class="text-end"><fmt:formatNumber value="${item.sousTotal}" type="currency" currencyCode="EUR"/></td>
          </tr>
        </c:forEach>
      </tbody>
      <tfoot>
        <tr>
          <th colspan="3" class="text-end">Total à payer</th>
          <th class="text-end"><fmt:formatNumber value="${montant}" type="currency" currencyCode="EUR"/></th>
        </tr>
      </tfoot>
    </table>
  </div>

  <div class="d-flex flex-wrap justify-content-between align-items-center gap-3 mt-4">
    <a href="/panier">Modifier le panier</a>
    <a href="/payer" class="btn btn-primary btn-lg">Passer au paiement</a>
  </div>
</div>

<jsp:include page="footer.jsp"/>
