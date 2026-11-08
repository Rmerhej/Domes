<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"><jsp:param name="titre" value="Recherche"/></jsp:include>

<div class="container section">
  <h1 class="titre-page">Résultats pour « <c:out value="${recherche}"/> »</h1>

  <c:choose>
    <c:when test="${empty articles}">
      <div class="vide mt-4">
        <p>Aucun animal ne porte ce nom. Essayez un autre nom, ou parcourez le catalogue.</p>
        <a href="/catalogue" class="btn btn-outline-primary">Voir tout le catalogue</a>
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
