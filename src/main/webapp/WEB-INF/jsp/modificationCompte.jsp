    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <jsp:include page="header.jsp"></jsp:include>

        <h1 class="text-center" style="color:orange">Modifier mes coordonnées</h1>

        <c:if test="${not empty succes}">
          <div class="alert alert-success"><c:out value="${succes}"/></div>
        </c:if>
        <c:if test="${not empty erreur}">
          <div class="alert alert-danger"><c:out value="${erreur}"/></div>
        </c:if>

        <form action="/modification" method="post">

          <div class="mb-3">
            <label class="form-label" for="nom">NOM :</label>
            <input class="form-control" type="text" id="nom" name="nom_client" value="<c:out value="${clientModifiant.nom_client}"/>">
          </div>

          <div class="mb-3">
            <label class="form-label" for="prenom">PRÉNOM :</label>
            <input class="form-control" type="text" id="prenom" name="prenom_client" value="<c:out value="${clientModifiant.prenom_client}"/>">
          </div>

          <div class="mb-3">
            <label class="form-label" for="tel">TÉLÉPHONE :</label>
            <input class="form-control" type="tel" id="tel" name="telephone_client" value="<c:out value="${clientModifiant.telephone_client}"/>">
          </div>

          <div class="mb-3">
            <label class="form-label" for="mail">EMAIL :</label>
            <input class="form-control" type="email" id="mail" name="email_client" value="<c:out value="${clientModifiant.email_client}"/>">
          </div>

          <div class="mb-3">
            <label class="form-label" for="login">LOGIN :</label>
            <input class="form-control" type="text" id="login" name="login" required value="<c:out value="${clientModifiant.login}"/>">
          </div>

          <div class="mb-3">
            <label class="form-label" for="nouveauMdp">NOUVEAU MOT DE PASSE :</label>
            <input class="form-control" type="password" id="nouveauMdp" name="nouveauMdp" autocomplete="new-password">
            <div class="form-text">Laissez vide pour garder votre mot de passe actuel.</div>
          </div>

          <div class="mb-3">
             <button type="submit" class="btn btn-primary">MODIFIER</button>
          </div><hr>
        </form>

    <jsp:include page="footer.jsp"></jsp:include>
