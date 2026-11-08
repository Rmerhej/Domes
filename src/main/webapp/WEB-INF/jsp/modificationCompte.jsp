<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"><jsp:param name="titre" value="Mon compte"/></jsp:include>

<div class="container section">
  <div class="carte-formulaire mx-auto">
    <div class="d-flex flex-wrap justify-content-between align-items-baseline gap-2">
      <h1 class="titre-page">Modifier mes coordonnées</h1>
      <a href="/mesCommandes">Voir mes commandes</a>
    </div>

    <c:if test="${not empty succes}">
      <div class="alert alert-success" role="status"><c:out value="${succes}"/></div>
    </c:if>
    <c:if test="${not empty erreur}">
      <div class="alert alert-danger" role="alert"><c:out value="${erreur}"/></div>
    </c:if>

    <form action="/modification" method="post">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <div class="row g-3">
        <div class="col-sm-6">
          <label class="form-label" for="nom">Nom</label>
          <input class="form-control" type="text" id="nom" name="nom_client" autocomplete="family-name" value="<c:out value="${clientModifiant.nom_client}"/>">
        </div>
        <div class="col-sm-6">
          <label class="form-label" for="prenom">Prénom</label>
          <input class="form-control" type="text" id="prenom" name="prenom_client" autocomplete="given-name" value="<c:out value="${clientModifiant.prenom_client}"/>">
        </div>
        <div class="col-sm-6">
          <label class="form-label" for="tel">Téléphone</label>
          <input class="form-control" type="tel" id="tel" name="telephone_client" autocomplete="tel" value="<c:out value="${clientModifiant.telephone_client}"/>">
        </div>
        <div class="col-sm-6">
          <label class="form-label" for="mail">E-mail</label>
          <input class="form-control" type="email" id="mail" name="email_client" autocomplete="email" value="<c:out value="${clientModifiant.email_client}"/>">
        </div>
        <div class="col-sm-6">
          <label class="form-label" for="login">Identifiant</label>
          <input class="form-control" type="text" id="login" name="login" autocomplete="username" required value="<c:out value="${clientModifiant.login}"/>">
        </div>
        <div class="col-sm-6">
          <label class="form-label" for="nouveauMdp">Nouveau mot de passe</label>
          <input class="form-control" type="password" id="nouveauMdp" name="nouveauMdp" autocomplete="new-password" aria-describedby="aide-mdp">
          <div class="form-text" id="aide-mdp">Laissez vide pour garder l'actuel.</div>
        </div>
      </div>
      <button type="submit" class="btn btn-primary mt-4">Enregistrer les modifications</button>
    </form>
  </div>
</div>

<jsp:include page="footer.jsp"/>
