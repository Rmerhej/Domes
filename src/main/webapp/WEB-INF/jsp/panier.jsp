    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="header.jsp"></jsp:include>

  <table class="table">
       <thead>
         <tr>
           <th>Article</th>
           <th>Prix</th>
           <th>Image</th>
         </tr>
       </thead>

       <tbody>
         <c:forEach var="article" items="${art}">
           <tr>
            <td><c:out value="${article.nom_article}" /></td>
            <td><c:out value="${article.prix_unitaire_article}" /></td>
            <td><img src="<c:out value="${article.image_article}" />"  height="75" width="75"  alt="<c:out value="${article.nom_article}" />"></td>
            <td><a href="/suppDuPanier?id=<c:out value="${article.id_article}" />" class="btn btn-success">Supprimer</a></td>
            <td><a href="/achat" class="btn btn-success">Acheter</a></td>
           </tr>
         </c:forEach>
       </tbody>

    </table>
    <jsp:include page="footer.jsp"></jsp:include>