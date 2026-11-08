<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"><jsp:param name="titre" value="Nous contacter"/></jsp:include>

<div class="container section">
  <div class="carte-formulaire mx-auto">
    <h1 class="titre-page">Nous contacter</h1>
    <p class="chapeau">Une question sur un animal ou une commande ? Écrivez-nous, nous répondons par e-mail.</p>

    <c:if test="${not empty succes}">
      <div class="alert alert-success" role="status"><c:out value="${succes}"/></div>
    </c:if>
    <c:if test="${not empty erreur}">
      <div class="alert alert-danger" role="alert"><c:out value="${erreur}"/></div>
    </c:if>

    <form action="/nousContacter" method="post">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <div class="row g-3">
        <div class="col-sm-6">
          <label for="nom" class="form-label">Nom</label>
          <input type="text" class="form-control" id="nom" name="nom" autocomplete="family-name" required value="<c:out value="${nom}"/>">
        </div>
        <div class="col-sm-6">
          <label for="prenom" class="form-label">Prénom <span class="text-secondary fw-normal">(facultatif)</span></label>
          <input type="text" class="form-control" id="prenom" name="prenom" autocomplete="given-name" value="<c:out value="${prenom}"/>">
        </div>
        <div class="col-12">
          <label for="mail" class="form-label">E-mail</label>
          <input type="email" class="form-control" id="mail" name="mail" autocomplete="email" required value="<c:out value="${mail}"/>">
        </div>
        <div class="col-12">
          <label for="message" class="form-label">Message</label>
          <textarea class="form-control" id="message" name="msg" rows="6" required><c:out value="${msg}"/></textarea>
        </div>
      </div>
      <button type="submit" class="btn btn-primary mt-4">Envoyer le message</button>
    </form>
  </div>
</div>

<jsp:include page="footer.jsp"/>
