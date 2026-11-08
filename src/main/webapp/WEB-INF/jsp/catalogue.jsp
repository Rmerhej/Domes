<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"><jsp:param name="titre" value="Catalogue"/></jsp:include>

<div class="container section">
  <h1 class="titre-page">Tous nos animaux</h1>
  <p class="chapeau">Pour affiner, choisissez une espèce dans le menu.</p>

  <c:choose>
    <c:when test="${empty articles}">
      <div class="vide mt-4">
        <p class="mb-0">Aucun animal n'est disponible pour le moment. Revenez bientôt.</p>
      </div>
    </c:when>
    <c:otherwise>
      <div class="row g-4 mt-1">
        <c:forEach items="${articles}" var="article">
          <div class="col-sm-6 col-lg-4 col-xl-3">
            <c:set var="carte" value="${article}" scope="request"/>
            <jsp:include page="_carteArticle.jsp"/>
          </div>
        </c:forEach>
      </div>
    </c:otherwise>
  </c:choose>
</div>

<jsp:include page="footer.jsp"/>
