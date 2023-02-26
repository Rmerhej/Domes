    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="header.jsp"></jsp:include>
    <table class="table">
        <thead>
           <tr>
              <th>Article</th>
              <th>Prix</th>
              <th>Image</th>
              <th>Quantité</th>
              <th>Prix Total</th>
            </tr>
         </thead>

        <tbody>
          <c:forEach var="article" items="${articles}">
              <tr>
                 <td><c:out value="${article.nom_article}" /></td>
                 <td><c:out value="${article.prix_unitaire_article}"/></td>
                 <td><img src="<c:url value="${article.image_article}"  />" height="75" width="75"/></td>
              </tr>

          </c:forEach>

                 <td><a href="/payer" class="btn btn-primary">Valider et Payer</a></td>
        </tbody>
   </table>














    <jsp:include page="footer.jsp"></jsp:include>