    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

          <tr>
           <td>  ${articleAjoute.nom_article}</td>
           <td>  ${articleAjoute.prix_unitaire_article}</td>
           <td><img src="${articleAjoute.image_article}" height="75" width="75"></td>
           <td><a href="/achat" class="btn btn-success">Acheter</a></td>
          </tr>

       </tbody>

    </table>
    <jsp:include page="footer.jsp"></jsp:include>