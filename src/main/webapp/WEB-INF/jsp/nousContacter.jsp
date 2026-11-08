    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <jsp:include page="header.jsp"></jsp:include>

        <h1 class="text-center" style="color:orange">Nous contacter</h1>

        <c:if test="${not empty succes}">
          <div class="alert alert-success"><c:out value="${succes}"/></div>
        </c:if>
        <c:if test="${not empty erreur}">
          <div class="alert alert-danger"><c:out value="${erreur}"/></div>
        </c:if>

        <form action="/nousContacter" method="post">
           <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
           <div class="mb-3">
             <label for="nom" class="form-label">NOM :</label>
             <input type="text" class="form-control" id="nom" name="nom" required value="<c:out value="${nom}"/>">
           </div>

           <div class="mb-3">
             <label for="prenom" class="form-label">PRÉNOM :</label>
             <input type="text" class="form-control" id="prenom" name="prenom" value="<c:out value="${prenom}"/>">
          </div>

          <div class="mb-3">
            <label for="mail" class="form-label">E-MAIL :</label>
            <input type="email" class="form-control" id="mail" name="mail" required value="<c:out value="${mail}"/>">
         </div>

         <div class="form-floating">
           <textarea class="form-control" placeholder="Votre message" id="message" name="msg" style="height: 150px" required><c:out value="${msg}"/></textarea>
           <label for="message">Votre message</label>
         </div><br>

         <button type="submit" class="btn btn-primary">Envoyer</button><hr>
       </form>

    <jsp:include page="footer.jsp"></jsp:include>
