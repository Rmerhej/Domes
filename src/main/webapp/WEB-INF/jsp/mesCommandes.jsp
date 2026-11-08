<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"><jsp:param name="titre" value="Mes commandes"/></jsp:include>

<div class="container section">
  <h1 class="titre-page">Mes commandes</h1>

  <c:choose>
    <c:when test="${empty commandes}">
      <div class="vide mt-3">
        <p>Vous n'avez pas encore passé de commande.</p>
        <a href="/catalogue" class="btn btn-primary">Découvrir nos animaux</a>
      </div>
    </c:when>
    <c:otherwise>
      <div class="tableau table-responsive mt-3">
        <table class="table align-middle">
          <thead>
            <tr>
              <th scope="col">N°</th>
              <th scope="col">Date</th>
              <th scope="col">Animaux</th>
              <th scope="col">Statut</th>
              <th scope="col" class="text-end">Total</th>
              <th scope="col"><span class="visually-hidden">Détail</span></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="commande" items="${commandes}">
              <tr>
                <td><c:out value="${commande.id}"/></td>
                <td><c:out value="${commande.dateFormatee}"/></td>
                <td><c:out value="${commande.nombreArticles}"/></td>
                <td><c:out value="${commande.statut.libelle}"/></td>
                <td class="text-end"><fmt:formatNumber value="${commande.montantTotal}" type="currency" currencyCode="EUR"/></td>
                <td class="text-end"><a href="/commande/confirmation?id=<c:out value="${commande.id}"/>">Détail<span class="visually-hidden"> de la commande <c:out value="${commande.id}"/></span></a></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </c:otherwise>
  </c:choose>
</div>

<jsp:include page="footer.jsp"/>
