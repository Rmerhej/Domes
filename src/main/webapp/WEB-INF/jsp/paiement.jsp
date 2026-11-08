<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"><jsp:param name="titre" value="Paiement"/></jsp:include>

<div class="container section">
  <div class="carte-formulaire mx-auto">
    <h1 class="titre-page">Paiement</h1>

    <div class="alert alert-info" role="note">
      Paiement simulé : aucune donnée bancaire n'est demandée ni enregistrée, aucun montant n'est débité.
    </div>

    <c:if test="${not empty erreur}">
      <div class="alert alert-danger" role="alert"><c:out value="${erreur}"/></div>
    </c:if>

    <p class="total">Montant à payer : <strong><fmt:formatNumber value="${montant}" type="currency" currencyCode="EUR"/></strong></p>

    <form action="/payer" method="post">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <div class="form-check mb-4">
        <input class="form-check-input" type="checkbox" id="cgv" name="cgv" value="ok" required>
        <label class="form-check-label" for="cgv">
          J'accepte les <a href="/cgv" target="_blank" rel="noopener">conditions générales de vente</a>
        </label>
      </div>
      <div class="d-flex flex-wrap justify-content-between align-items-center gap-3">
        <a href="/achat">Retour au récapitulatif</a>
        <button type="submit" class="btn btn-primary btn-lg">Payer <fmt:formatNumber value="${montant}" type="currency" currencyCode="EUR"/></button>
      </div>
    </form>
  </div>
</div>

<jsp:include page="footer.jsp"/>
