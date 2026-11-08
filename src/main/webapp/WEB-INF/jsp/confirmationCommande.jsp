<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"><jsp:param name="titre" value="Commande n° ${commande.id}"/></jsp:include>

<div class="container section">
  <h1 class="titre-page">Commande n° <c:out value="${commande.id}"/></h1>
  <p class="chapeau">
    Passée le <c:out value="${commande.dateFormatee}"/>.
    Statut : <strong><c:out value="${commande.statut.libelle}"/></strong>.
    Merci pour votre confiance !
  </p>

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
        <c:forEach var="ligne" items="${commande.lignes}">
          <tr>
            <td>
              <div class="d-flex align-items-center gap-3">
                <img src="<c:url value="/${ligne.article.image_article}"/>" alt="">
                <strong><c:out value="${ligne.article.nom_article}"/></strong>
              </div>
            </td>
            <td><fmt:formatNumber value="${ligne.prixUnitaire}" type="currency" currencyCode="EUR"/></td>
            <td><c:out value="${ligne.quantite}"/></td>
            <td class="text-end"><fmt:formatNumber value="${ligne.sousTotal}" type="currency" currencyCode="EUR"/></td>
          </tr>
        </c:forEach>
      </tbody>
      <tfoot>
        <tr>
          <th colspan="3" class="text-end">Total payé</th>
          <th class="text-end"><fmt:formatNumber value="${commande.montantTotal}" type="currency" currencyCode="EUR"/></th>
        </tr>
      </tfoot>
    </table>
  </div>

  <div class="d-flex flex-wrap gap-3 mt-4">
    <a href="/mesCommandes" class="btn btn-outline-primary">Toutes mes commandes</a>
    <a href="/accueil" class="btn btn-primary">Retour à l'accueil</a>
  </div>
</div>

<jsp:include page="footer.jsp"/>
