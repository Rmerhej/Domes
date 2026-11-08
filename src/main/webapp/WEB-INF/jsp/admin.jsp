<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"><jsp:param name="titre" value="Administration"/></jsp:include>

<div class="container section">
  <div class="entete-section">
    <h1 class="titre-page mb-0">Gérer les animaux</h1>
    <a href="/admin/articles/nouveau" class="btn btn-primary"><i class="bi bi-plus-lg" aria-hidden="true"></i> Ajouter un animal</a>
  </div>

  <c:if test="${not empty succes}">
    <div class="alert alert-success" role="status"><c:out value="${succes}"/></div>
  </c:if>
  <c:if test="${not empty erreur}">
    <div class="alert alert-danger" role="alert"><c:out value="${erreur}"/></div>
  </c:if>

  <c:choose>
    <c:when test="${empty articles}">
      <div class="vide">
        <p>Aucun animal en vente pour le moment.</p>
        <a href="/admin/articles/nouveau" class="btn btn-primary">Ajouter le premier</a>
      </div>
    </c:when>
    <c:otherwise>
      <div class="tableau table-responsive">
        <table class="table align-middle">
          <thead>
            <tr>
              <th scope="col">Animal</th>
              <th scope="col">Espèce et race</th>
              <th scope="col" class="text-end">Prix</th>
              <th scope="col"><span class="visually-hidden">Actions</span></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="a" items="${articles}">
              <tr>
                <td>
                  <div class="d-flex align-items-center gap-3">
                    <img src="<c:url value="/${a.image_article}"/>" alt="">
                    <a href="/animal/${a.id_article}"><strong><c:out value="${a.nom_article}"/></strong></a>
                  </div>
                </td>
                <td>
                  <c:out value="${a.categorie.nom_categorie}"/>
                  <c:if test="${not empty a.produit}"><span class="text-secondary">, <c:out value="${a.produit.nom_produit}"/></span></c:if>
                </td>
                <td class="text-end"><fmt:formatNumber value="${a.prix_unitaire_article}" type="currency" currencyCode="EUR"/></td>
                <td class="text-end text-nowrap">
                  <a href="/admin/articles/${a.id_article}" class="btn btn-outline-primary btn-sm">Modifier<span class="visually-hidden"> <c:out value="${a.nom_article}"/></span></a>
                  <form action="/admin/articles/${a.id_article}/supprimer" method="post" class="d-inline"
                        onsubmit="return confirm('Supprimer définitivement cet animal ?');">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-link text-danger btn-sm">Supprimer<span class="visually-hidden"> <c:out value="${a.nom_article}"/></span></button>
                  </form>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </c:otherwise>
  </c:choose>
</div>

<jsp:include page="footer.jsp"/>
