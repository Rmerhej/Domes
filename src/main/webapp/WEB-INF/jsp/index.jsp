<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>

<div class="container">
  <section class="heros">
    <div>
      <h1>Un compagnon vous attend</h1>
      <p class="chapeau fs-5">Chiens, chats, poissons, reptiles ou oiseaux : parcourez nos animaux par espèce
        et réservez celui qui rejoindra votre famille.</p>
      <div class="d-flex flex-wrap gap-2 mt-3">
        <a href="/catalogue" class="btn btn-primary btn-lg">Voir tous les animaux</a>
        <c:if test="${sessionScope.status != 'connected'}">
          <a href="/newCompte" class="btn btn-outline-primary btn-lg">Créer un compte</a>
        </c:if>
      </div>
    </div>
    <img src="/images/pexels-katarzyna-modrzejewska-1314550.jpg" alt="Un chat installé confortablement" fetchpriority="high">
  </section>

  <section class="section" aria-labelledby="titre-especes">
    <h2 id="titre-especes" class="mb-4">Choisir par espèce</h2>
    <ul class="medaillons">
      <li><a class="medaillon" href="/tocateg?name=1"><img src="/images/German Shepherd.jpg" alt="">Chiens</a></li>
      <li><a class="medaillon" href="/tocateg?name=2"><img src="/images/the-red-or-white-cat-i-on-white-studio.jpg" alt="">Chats</a></li>
      <li><a class="medaillon" href="/tocateg?name=4"><img src="/images/Goldfish.jpg" alt="">Poissons</a></li>
      <li><a class="medaillon" href="/tocateg?name=5"><img src="/images/green-lizard-on-branch-green-lizard-sunbathing-on-branch-green-lizard-climb-on-wood.jpg" alt="">Reptiles</a></li>
      <li><a class="medaillon" href="/tocateg?name=6"><img src="/images/closeup-shot-of-male-hawfinch-sitting-on-branch.jpg" alt="">Oiseaux</a></li>
    </ul>
  </section>
</div>

<c:if test="${not empty animaux}">
  <section class="section section-surface" aria-labelledby="titre-une">
    <div class="container">
      <div class="entete-section">
        <h2 id="titre-une">Ils cherchent une famille</h2>
        <a href="/catalogue">Tout le catalogue</a>
      </div>
      <div class="row g-4">
        <c:forEach var="article" items="${animaux}">
          <div class="col-sm-6 col-lg-3">
            <c:set var="carte" value="${article}" scope="request"/>
            <jsp:include page="_carteArticle.jsp"/>
          </div>
        </c:forEach>
      </div>
    </div>
  </section>
</c:if>

<jsp:include page="footer.jsp"/>
