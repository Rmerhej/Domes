<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Début commun de toutes les pages : <head>, barre de navigation, ouverture de <main>.
  Utilisation dans une page :
    <jsp:include page="header.jsp"><jsp:param name="titre" value="Mon panier"/></jsp:include>
    ... contenu ...
    <jsp:include page="footer.jsp"/>
--%>
<%-- Prix en euros au format français : voir localeResolver dans ApplicationConfig --%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title><c:if test="${not empty param.titre}"><c:out value="${param.titre}"/> | </c:if>Domes, animalerie en ligne</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Fredoka:wght@500;600&family=Source+Sans+3:wght@400;600;700&display=swap" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
  <link href="/css/domes.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg barre-nav sticky-top">
  <div class="container">
    <a class="navbar-brand" href="/accueil"><img src="/images/logo_DOMES_SERVICE.png" alt="Domes, retour à l'accueil"></a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menu"
            aria-controls="menu" aria-expanded="false" aria-label="Ouvrir le menu">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="menu">
      <%-- Les id correspondent aux catégories en base (voir data.sql) --%>
      <ul class="navbar-nav me-auto gap-lg-2">
        <li class="nav-item"><a class="nav-link ${param.name == '1' ? 'active' : ''}" href="/tocateg?name=1">Chiens</a></li>
        <li class="nav-item"><a class="nav-link ${param.name == '2' ? 'active' : ''}" href="/tocateg?name=2">Chats</a></li>
        <li class="nav-item"><a class="nav-link ${param.name == '4' ? 'active' : ''}" href="/tocateg?name=4">Poissons</a></li>
        <li class="nav-item"><a class="nav-link ${param.name == '5' ? 'active' : ''}" href="/tocateg?name=5">Reptiles</a></li>
        <li class="nav-item"><a class="nav-link ${param.name == '6' ? 'active' : ''}" href="/tocateg?name=6">Oiseaux</a></li>
      </ul>

      <form class="d-flex recherche my-2 my-lg-0 me-lg-3" action="/search" method="get" role="search">
        <label for="recherche" class="visually-hidden">Rechercher un animal par son nom</label>
        <input class="form-control" type="search" id="recherche" name="search" placeholder="Un nom : Fidel, Nemo…"
               value="<c:out value="${param.search}"/>">
        <button class="btn btn-primary" type="submit" title="Rechercher"><i class="bi bi-search" aria-hidden="true"></i><span class="visually-hidden">Rechercher</span></button>
      </form>

      <div class="d-flex align-items-center gap-1">
        <%-- Visible seulement pour les administrateurs (rôle ADMIN, voir SecurityConfig) --%>
        <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
          <a class="icone-nav" href="/admin" title="Administration"><i class="bi bi-gear" aria-hidden="true"></i><span class="visually-hidden">Administration</span></a>
        </c:if>
        <c:choose>
          <c:when test="${sessionScope.status == 'connected'}">
            <a class="icone-nav" href="/modifierCompte" title="Mon compte"><i class="bi bi-person-circle" aria-hidden="true"></i><span class="visually-hidden">Mon compte</span></a>
          </c:when>
          <c:otherwise>
            <a class="icone-nav" href="/seConnecter" title="Se connecter"><i class="bi bi-person" aria-hidden="true"></i><span class="visually-hidden">Se connecter</span></a>
          </c:otherwise>
        </c:choose>

        <a class="icone-nav" href="/panier" title="Mon panier">
          <i class="bi bi-basket2" aria-hidden="true"></i><span class="visually-hidden">Mon panier</span>
          <c:if test="${sessionScope.cartCount > 0}">
            <span class="badge rounded-pill"><c:out value="${sessionScope.cartCount}"/><span class="visually-hidden"> articles</span></span>
          </c:if>
        </a>

        <c:if test="${sessionScope.status == 'connected'}">
          <%-- Déconnexion en POST avec jeton CSRF (exigé par Spring Security) --%>
          <form action="/deconnection" method="post" class="d-inline m-0">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="icone-nav" title="Se déconnecter"><i class="bi bi-box-arrow-right" aria-hidden="true"></i><span class="visually-hidden">Se déconnecter</span></button>
          </form>
        </c:if>
      </div>
    </div>
  </div>
</nav>

<main>
<%-- Messages communs à toutes les pages --%>
<c:if test="${not empty message or not empty deconnecte or not empty nonConnecte}">
  <div class="container mt-3">
    <c:if test="${not empty message}"><div class="alert alert-success mb-0" role="status"><c:out value="${message}"/></div></c:if>
    <c:if test="${not empty deconnecte}"><div class="alert alert-info mb-0" role="status"><c:out value="${deconnecte}"/></div></c:if>
    <c:if test="${not empty nonConnecte}"><div class="alert alert-warning mb-0" role="status"><c:out value="${nonConnecte}"/></div></c:if>
  </div>
</c:if>
