    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css" />
    <!DOCTYPE html>
    <html lang="fr">
    <head>
    <meta charset="UTF-8">
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
        crossorigin="anonymous">
    <title>newCompte.jsp</title>
    </head>
    <body class="container">
    <jsp:include page="header.jsp"></jsp:include>

    <hr>
    <h1 class="text-center" style="color:orange">Création de votre Compte Client</h1>
        <c:if test="${existance}">
                             <script>
                                 Toastify({
                                   text: "${existingLogin}",
                                   duration: 5000,
                                   gravity: "top",
                                   position: "middle",
                                   backgroundColor: "linear-gradient(to right, #00b09b, #96c93d)",
                                   stopOnFocus: true,
                                     }).showToast();
                             </script>
              </c:if>
    <div>
     <form:form method="post" action="/newClientCompte" modelAttribute="newclient"  class="mb-3" >
          <div class="form-group">
            <label class="form-label">Nom :</label>
            <form:input path="nom_client" type="text" class="form-control custom-input"/>
             <form:errors path="nom_client" cssClass="error"/>
          </div>

          <div class="form-group">
             <label class="form-label">Prénom :</label>
             <form:input path="prenom_client" type="text" class="form-control custom-input"/>
             <form:errors path="prenom_client" cssClass="error"/>
          </div>

          <div class="form-group">
             <label class="form-label">Téléphone :</label>
             <form:input path="telephone_client" type="text" class="form-control custom-input"/>
             <form:errors path="telephone_client" cssClass="error"/>
          </div><br/>

          <div class="form-group">
             <label class="form-label">Email :</label>
             <form:input path="email_client" type="text" class="form-control custom-input"/>
             <form:errors path="email_client" cssClass="error"/>
          </div>

          <div class="form-group">
             <label class="form-label">Login :</label>
             <form:input path="login" type="text" class="form-control custom-input"/>
             <form:errors path="login" cssClass="error"/>
          </div>

          <div class="form-group">
              <label class="form-label">Mot de passe :</label>
              <form:input path="mdp" type="text" class="form-control custom-input"/>
              <form:errors path="mdp" cssClass="error"/>
          </div>

          <div class="form-group"><br>
              <input type="submit" value="Submit" id="submit" class="btn btn-danger">
          </div>

           <style>
             .custom-input {width: 30%;}

              form{background-image: url('images/pexels-roshan-kamath-1661179.jpg');
              background-size: cover;
              background-repeat: no-repeat;
              }
           </style>
     </form:form><br>
    </div><br><br>
    <jsp:include page="footer.jsp"></jsp:include>

