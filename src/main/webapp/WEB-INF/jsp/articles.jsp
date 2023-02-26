    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
          <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="header.jsp"></jsp:include>
    <h1  class="text-center" style="color: orange">Articles recherchés</h1><hr>

    <table class="table">
        <thead>
           <tr>
             <th>Image</th>
             <th>Nom</th>
             <th>Prix</th>
             <th>Ajouter au Panier</th>
             <th>Acheter</th>
           </tr>
        </thead>

         <tbody>
           <c:forEach var="foundarticle" items="${foundarticles}">
             <tr>
               <td><img src="<c:url value="${foundarticle.image_article}" />" height="75" width="75"/></td>
               <td><c:out value="${foundarticle.nom_article}" /></td>
               <td><c:out value="${foundarticle.prix_unitaire_article}"/></td>
               <td><a href="/panier" >  <img src="images/bitcoin.png" height="25" width="25" alt="panier"></a></td>
               <td><a href="/achat" >	<img alt="carte de crédit" src="images/carte-bancaire.png" height="75" width="75"></a></td>
             </tr>
           </c:forEach>
        </tbody>

    </table>
    <jsp:include page="footer.jsp"></jsp:include>