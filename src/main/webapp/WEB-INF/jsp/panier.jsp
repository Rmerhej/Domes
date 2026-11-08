<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"><jsp:param name="titre" value="Mon panier"/></jsp:include>

<div class="container section">
  <h1 class="titre-page">Mon panier</h1>

  <c:if test="${not empty erreur}">
    <div class="alert alert-danger" role="alert"><c:out value="${erreur}"/></div>
  </c:if>

  <c:choose>
    <c:when test="${empty items}">
      <div class="vide mt-3">
        <p>Votre panier est vide.</p>
        <a href="/catalogue" class="btn btn-primary">Découvrir nos animaux</a>
      </div>
    </c:when>
    <c:otherwise>
      <div class="tableau table-responsive mt-3">
        <table class="table align-middle">
          <thead>
            <tr>
              <th scope="col">Animal</th>
              <th scope="col">Prix unitaire</th>
              <th scope="col">Quantité</th>
              <th scope="col" class="text-end">Sous-total</th>
              <th scope="col"><span class="visually-hidden">Actions</span></th>
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
                <td class="text-end">
                  <a href="/suppDuPanier?id=<c:out value="${item.article.id_article}"/>" class="btn btn-link text-danger btn-sm">
                    Retirer<span class="visually-hidden"> <c:out value="${item.article.nom_article}"/></span>
                  </a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>

      <div class="d-flex flex-wrap justify-content-between align-items-center gap-3 mt-4">
        <a href="/catalogue">Continuer mes achats</a>
        <div class="d-flex flex-wrap align-items-center gap-3">
          <span class="total">Total : <strong><fmt:formatNumber value="${montant}" type="currency" currencyCode="EUR"/></strong></span>
          <a href="/achat" class="btn btn-primary btn-lg">Passer la commande</a>
        </div>
      </div>
    </c:otherwise>
  </c:choose>
</div>

<jsp:include page="footer.jsp"/>
