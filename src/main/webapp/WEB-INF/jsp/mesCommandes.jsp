    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <jsp:include page="header.jsp"></jsp:include>

    <h2 class="my-3">Mes commandes</h2>

    <c:choose>
      <c:when test="${empty commandes}">
        <p>Vous n'avez pas encore passé de commande.</p>
        <a href="/accueil" class="btn btn-primary mb-4">Découvrir nos animaux</a>
      </c:when>
      <c:otherwise>
        <table class="table align-middle">
          <thead>
            <tr>
              <th>N°</th>
              <th>Date</th>
              <th>Articles</th>
              <th>Total</th>
              <th>Statut</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="commande" items="${commandes}">
              <tr>
                <td><c:out value="${commande.id}"/></td>
                <td><c:out value="${commande.dateFormatee}"/></td>
                <td><c:out value="${commande.nombreArticles}"/></td>
                <td><fmt:formatNumber value="${commande.montantTotal}" type="currency" currencySymbol="€" /></td>
                <td><span class="badge bg-success"><c:out value="${commande.statut.libelle}"/></span></td>
                <td><a href="/commande/confirmation?id=<c:out value="${commande.id}"/>" class="btn btn-outline-secondary btn-sm">Détail</a></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:otherwise>
    </c:choose>

    <jsp:include page="footer.jsp"></jsp:include>
