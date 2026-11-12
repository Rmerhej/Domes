<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- Fiche détaillée d'un animal (AnimalController) --%>
<jsp:include page="header.jsp"><jsp:param name="titre" value="${article.nom_article}"/></jsp:include>

<div class="container section">
  <c:if test="${not empty article.categorie}">
    <nav aria-label="Fil d'Ariane" class="mb-3">
      <ol class="breadcrumb mb-0">
        <li class="breadcrumb-item"><a href="/accueil">Accueil</a></li>
        <li class="breadcrumb-item"><a href="/tocateg?name=${article.categorie.id_categorie}"><c:out value="${article.categorie.nom_categorie}"/></a></li>
        <li class="breadcrumb-item active" aria-current="page"><c:out value="${article.nom_article}"/></li>
      </ol>
    </nav>
  </c:if>

  <div class="fiche">
    <img class="fiche-photo" src="<c:url value="/${article.image_article}"/>" alt="Photo de <c:out value="${article.nom_article}"/>">

    <div>
      <h1 class="titre-page mb-1"><c:out value="${article.nom_article}"/></h1>
      <c:if test="${not empty article.produit}">
        <p class="espece fs-5 mb-3"><c:out value="${article.produit.nom_produit}"/></p>
      </c:if>

      <p class="prix fs-3 mb-4"><fmt:formatNumber value="${article.prix_unitaire_article}" type="currency" currencyCode="EUR"/></p>

      <c:choose>
        <c:when test="${not empty article.description}">
          <p class="texte-court" style="white-space: pre-line"><c:out value="${article.description}"/></p>
        </c:when>
        <c:otherwise>
          <p class="texte-court text-secondary">Pas encore de description pour cet animal. Écrivez-nous pour en savoir plus.</p>
        </c:otherwise>
      </c:choose>

      <c:if test="${not empty erreur}">
        <div class="alert alert-danger mt-4" role="alert"><c:out value="${erreur}"/></div>
      </c:if>

      <c:choose>
      <c:when test="${not article.disponible}">
        <p class="adopte-texte fs-5 mt-4"><i class="bi bi-house-heart" aria-hidden="true"></i>
          <c:out value="${article.nom_article}"/> a déjà trouvé une famille.</p>
        <a href="${empty article.categorie ? '/catalogue' : ('/tocateg?name=' += article.categorie.id_categorie)}" class="btn btn-outline-primary">Voir les autres animaux disponibles</a>
      </c:when>
      <c:otherwise>
      <form action="/panier" method="post" class="d-flex flex-wrap align-items-end gap-3 mt-4">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="idArticle" value="<c:out value="${article.id_article}"/>">
        <div>
          <label class="form-label" for="quantite">Quantité</label>
          <input class="form-control" type="number" id="quantite" name="quantite" value="1" min="1" max="20" style="width: 6rem">
        </div>
        <button type="submit" class="btn btn-primary btn-lg"><i class="bi bi-basket2" aria-hidden="true"></i> Ajouter au panier</button>
      </form>
      </c:otherwise>
      </c:choose>

      <p class="mt-4 mb-0"><a href="/nousContacter">Une question sur <c:out value="${article.nom_article}"/> ? Contactez-nous</a></p>
    </div>
  </div>
</div>

<c:if test="${not empty autres}">
  <section class="section section-surface" aria-labelledby="titre-autres">
    <div class="container">
      <div class="entete-section">
        <h2 id="titre-autres">Ils pourraient aussi vous plaire</h2>
        <a href="/tocateg?name=${article.categorie.id_categorie}">Tous les <c:out value="${article.categorie.nom_categorie}"/></a>
      </div>
      <div class="row g-4">
        <c:forEach var="autre" items="${autres}">
          <div class="col-sm-6 col-lg-4">
            <c:set var="carte" value="${autre}" scope="request"/>
            <jsp:include page="_carteArticle.jsp"/>
          </div>
        </c:forEach>
      </div>
    </div>
  </section>
</c:if>

<jsp:include page="footer.jsp"/>
