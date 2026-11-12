<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Carte d'un animal, réutilisée par l'accueil, le catalogue, les races et la recherche.
  Avant l'include, la page place l'article dans la requête :
    <c:set var="carte" value="${article}" scope="request"/>
    <jsp:include page="_carteArticle.jsp"/>
--%>
<article class="carte-animal ${carte.disponible ? '' : 'adopte'}">
  <%-- La photo mène à la fiche ; le lien du titre suffit aux lecteurs d'écran --%>
  <a href="/animal/${carte.id_article}" class="photo" tabindex="-1" aria-hidden="true"><img src="<c:url value="/${carte.image_article}"/>" alt="" loading="lazy">
    <c:if test="${not carte.disponible}"><span class="etiquette etiquette-adopte">Adopté</span></c:if></a>
  <div class="corps">
    <h3 class="titre-animal"><a class="lien-fiche" href="/animal/${carte.id_article}"><c:out value="${carte.nom_article}"/></a></h3>
    <c:if test="${not empty carte.produit}">
      <span class="espece"><c:out value="${carte.produit.nom_produit}"/></span>
    </c:if>
    <span class="prix"><fmt:formatNumber value="${carte.prix_unitaire_article}" type="currency" currencyCode="EUR"/></span>
    <c:choose>
    <c:when test="${not carte.disponible}">
      <p class="adopte-texte">Déjà adopté<span class="visually-hidden"> : <c:out value="${carte.nom_article}"/> n'est plus en vente</span></p>
    </c:when>
    <c:otherwise>
    <form action="/panier" method="post">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <input type="hidden" name="idArticle" value="<c:out value="${carte.id_article}"/>">
      <label class="visually-hidden" for="qte-${carte.id_article}">Quantité</label>
      <input class="form-control form-control-sm" type="number" id="qte-${carte.id_article}" name="quantite" value="1" min="1" max="20">
      <button type="submit" class="btn btn-primary btn-sm flex-grow-1 text-nowrap">
        <i class="bi bi-basket2" aria-hidden="true"></i> Ajouter<span class="visually-hidden"> <c:out value="${carte.nom_article}"/> au panier</span>
      </button>
    </form>
    </c:otherwise>
    </c:choose>
  </div>
</article>
