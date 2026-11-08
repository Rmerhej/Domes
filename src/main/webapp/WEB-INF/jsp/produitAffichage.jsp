<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Page d'une espèce : races en filtres + animaux (voir CategorieController) --%>
<jsp:include page="header.jsp"><jsp:param name="titre" value="${categorie.nom_categorie}"/></jsp:include>

<div class="container section">
  <h1 class="titre-page"><c:out value="${categorie.nom_categorie}"/></h1>

  <c:if test="${not empty produits}">
    <nav aria-label="Filtrer par race">
      <ul class="puces mt-3">
        <li>
          <c:url var="toutes" value="/tocateg"><c:param name="name" value="${categorie.id_categorie}"/></c:url>
          <a href="${toutes}" class="${empty race ? 'actif' : ''}" ${empty race ? 'aria-current="page"' : ''}>Toutes les races</a>
        </li>
        <c:forEach var="produit" items="${produits}">
          <li>
            <c:url var="lien" value="/tocateg">
              <c:param name="name" value="${categorie.id_categorie}"/>
              <c:param name="race" value="${produit.nom_produit}"/>
            </c:url>
            <a href="${lien}" class="${race == produit.nom_produit ? 'actif' : ''}" ${race == produit.nom_produit ? 'aria-current="page"' : ''}><c:out value="${produit.nom_produit}"/></a>
          </li>
        </c:forEach>
      </ul>
    </nav>
  </c:if>

  <c:choose>
    <c:when test="${empty articles}">
      <div class="vide mt-4">
        <p>Aucun animal de cette <c:out value="${empty race ? 'espèce' : 'race'}"/> n'est disponible pour le moment.</p>
        <a href="/catalogue" class="btn btn-outline-primary">Voir tout le catalogue</a>
      </div>
    </c:when>
    <c:otherwise>
      <div class="row g-4 mt-1">
        <c:forEach items="${articles}" var="article">
          <div class="col-sm-6 col-lg-4 col-xl-3">
            <c:set var="carte" value="${article}" scope="request"/>
            <jsp:include page="_carteArticle.jsp"/>
          </div>
        </c:forEach>
      </div>
    </c:otherwise>
  </c:choose>
</div>

<jsp:include page="footer.jsp"/>
