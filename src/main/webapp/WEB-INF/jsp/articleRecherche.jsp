    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
          <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="header.jsp"></jsp:include>
    <h1  class="text-center" style="color: orange">Article recherché</h1><hr>
     <h2>Articles</h2>
    <table class="table">
                <thead>
                    <tr>
                        <th>Article</th>
                        <th>Prix</th>
                        <th>Image</th>
                        <th>Ajouter au Panier</th>
                        <th>Acheter</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="article" items="${articles}">
                    <tr>
                        <td><c:out value="${article.nom_article}" /></td>
                        <td><c:out value="${article.prix_unitaire_article}"/></td>
                        <td><img src="<c:url value="${article.image_article}" />" height="75" width="75"/></td>
                        <td><form action="/panier?name=${article.nom_article}" method="post"><button type="submit"><img src="images/bitcoin.png" height="25" width="25" alt="panier"></button></form></td>
                        <td><a href="/achat" >	<img alt="carte de crédit"src="images/carte-bancaire.png"height="75" width="75"></a></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
    <jsp:include page="footer.jsp"></jsp:include>