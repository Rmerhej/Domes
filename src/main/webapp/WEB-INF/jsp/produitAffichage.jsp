    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
         crossorigin="anonymous">
    <link rel="stylesheet" href="/css/index.css"/>

    <jsp:include page="header.jsp"></jsp:include>

      <table class="table table-striped"">
           <thead >
               <tr >
                   <th>Produit</th>
                   <th>Consulter les articles par Race</th>

                </tr>
           </thead>
            <tbody>
                <c:forEach var="produit" items="${produits}">
                   <tr>
                      <td ><c:out value="${produit.nom_produit}" /></td>
                      <td>
                        <form action="/articlesParRace" method="post">
                           <input type="hidden" name="idCategorie" value=<c:out value="${produit.getCategorie().id_categorie}"/> />
                           <input type="hidden" name="nomProduit" value=<c:out value="${produit.nom_produit}"/> />
                          <button type="submit" class="btn btn-primary">Consulter</button>
                        </form>
                      </td>
                  </tr>
                </c:forEach>
            </tbody>
      </table>
    <jsp:include page="footer.jsp"></jsp:include>