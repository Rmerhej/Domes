<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"><jsp:param name="titre" value="Connexion"/></jsp:include>

<div class="container section">
  <div class="carte-formulaire etroite mx-auto">
    <h1 class="titre-page">Connexion</h1>

    <%-- ?inscrit : après création du compte / ?erreur : ajouté par Spring Security --%>
    <c:if test="${param.inscrit != null}">
      <div class="alert alert-success" role="status">Votre compte est créé. Connectez-vous pour continuer.</div>
    </c:if>
    <c:if test="${param.erreur != null}">
      <div class="alert alert-danger" role="alert">Identifiant ou mot de passe incorrect.</div>
    </c:if>

    <%-- <form:form> ajoute tout seul le jeton CSRF --%>
    <form:form method="post" action="/connection" modelAttribute="clientc">
      <div class="mb-3">
        <label class="form-label" for="login">Identifiant</label>
        <form:input path="login" id="login" cssClass="form-control" autocomplete="username" required="required" autofocus="autofocus"/>
      </div>
      <div class="mb-4">
        <label class="form-label" for="mdp">Mot de passe</label>
        <form:password path="mdp" id="mdp" cssClass="form-control" autocomplete="current-password" required="required"/>
      </div>
      <button type="submit" class="btn btn-primary w-100">Se connecter</button>
    </form:form>

    <p class="mt-4 mb-0 text-center">Pas encore de compte ? <a href="/newCompte">Créer un compte</a></p>
  </div>
</div>

<jsp:include page="footer.jsp"/>
