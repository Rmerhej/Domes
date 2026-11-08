<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Carte d'un animal, réutilisée par l'accueil, le catalogue, les races et la recherche.
  Avant l'include, la page place l'article dans la requête :
    <c:set var="carte" value="${article}" scope="request"/>
    <jsp:include page="_carteArticle.jsp"/>
--%>
<article class="carte-animal">
  <img src="<c:url value="/${carte.image_article}"/>" alt="Photo de <c:out value="${carte.nom_article}"/>" loading="lazy">
  <div class="corps">
    <h3 class="titre-animal"><c:out value="${carte.nom_article}"/></h3>
    <c:if test="${not empty carte.produit}">
      <span class="espece"><c:out value="${carte.produit.nom_produit}"/></span>
    </c:if>
    <span class="prix"><fmt:formatNumber value="${carte.prix_unitaire_article}" type="currency" currencyCode="EUR"/></span>
    <form action="/panier" method="post">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <input type="hidden" name="idArticle" value="<c:out value="${carte.id_article}"/>">
      <label class="visually-hidden" for="qte-${carte.id_article}">Quantité</label>
      <input class="form-control form-control-sm" type="number" id="qte-${carte.id_article}" name="quantite" value="1" min="1" max="20">
      <button type="submit" class="btn btn-primary btn-sm flex-grow-1 text-nowrap">
        <i class="bi bi-basket2" aria-hidden="true"></i> Ajouter<span class="visually-hidden"> <c:out value="${carte.nom_article}"/> au panier</span>
      </button>
    </form>
  </div>
</article>
