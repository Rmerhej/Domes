<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"><jsp:param name="titre" value="Créer un compte"/></jsp:include>

<div class="container section">
  <div class="carte-formulaire mx-auto">
    <h1 class="titre-page">Créer un compte</h1>
    <p class="chapeau">Il vous permet de réserver un animal et de suivre vos commandes.</p>

    <c:if test="${not empty existingLogin}">
      <div class="alert alert-danger" role="alert"><c:out value="${existingLogin}"/></div>
    </c:if>

    <form:form method="post" action="/newClientCompte" modelAttribute="newclient">
      <div class="row g-3">
        <div class="col-sm-6">
          <label class="form-label" for="nom_client">Nom</label>
          <form:input path="nom_client" id="nom_client" cssClass="form-control" autocomplete="family-name" required="required"/>
          <form:errors path="nom_client" cssClass="text-danger small"/>
        </div>
        <div class="col-sm-6">
          <label class="form-label" for="prenom_client">Prénom</label>
          <form:input path="prenom_client" id="prenom_client" cssClass="form-control" autocomplete="given-name" required="required"/>
          <form:errors path="prenom_client" cssClass="text-danger small"/>
        </div>
        <div class="col-sm-6">
          <label class="form-label" for="telephone_client">Téléphone</label>
          <form:input path="telephone_client" id="telephone_client" type="tel" cssClass="form-control" autocomplete="tel"/>
          <form:errors path="telephone_client" cssClass="text-danger small"/>
        </div>
        <div class="col-sm-6">
          <label class="form-label" for="email_client">E-mail</label>
          <form:input path="email_client" id="email_client" type="email" cssClass="form-control" autocomplete="email" required="required"/>
          <form:errors path="email_client" cssClass="text-danger small"/>
        </div>
        <div class="col-sm-6">
          <label class="form-label" for="login">Identifiant</label>
          <form:input path="login" id="login" cssClass="form-control" autocomplete="username" required="required"/>
          <form:errors path="login" cssClass="text-danger small"/>
        </div>
        <div class="col-sm-6">
          <label class="form-label" for="mdp">Mot de passe</label>
          <form:password path="mdp" id="mdp" cssClass="form-control" autocomplete="new-password" required="required"/>
          <form:errors path="mdp" cssClass="text-danger small"/>
        </div>
      </div>
      <button type="submit" class="btn btn-primary w-100 mt-4">Créer mon compte</button>
    </form:form>

    <p class="mt-4 mb-0 text-center">Déjà client ? <a href="/seConnecter">Se connecter</a></p>
  </div>
</div>

<jsp:include page="footer.jsp"/>
