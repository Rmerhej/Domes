    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <jsp:include page="header.jsp"></jsp:include>

    <h2 class="my-3">Paiement</h2>

    <div class="alert alert-info">
      <strong>Paiement simulé.</strong> Ce site est un projet de formation :
      aucune donnée bancaire n'est demandée ni enregistrée, aucun montant n'est débité.
    </div>

    <c:if test="${not empty erreur}">
      <div class="alert alert-danger"><c:out value="${erreur}"/></div>
    </c:if>

    <p class="fs-5">Montant à payer :
      <strong><fmt:formatNumber value="${montant}" type="currency" currencySymbol="€" /></strong>
    </p>

    <form action="/payer" method="post" class="mb-4"><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <div class="form-check mb-3">
        <input class="form-check-input" type="checkbox" id="cgv" name="cgv" value="ok" required>
        <label class="form-check-label" for="cgv">
          J'accepte les <a href="/cgv" target="_blank">conditions générales de vente</a>
        </label>
      </div>
      <a href="/achat" class="btn btn-outline-secondary">Retour</a>
      <button type="submit" class="btn btn-success">Payer (simulation)</button>
    </form>

   <jsp:include page="footer.jsp"></jsp:include>
