    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <!DOCTYPE html>
    <html lang="fr">
    <head>
    <meta charset="UTF-8">
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
        crossorigin="anonymous">

    <title>connection.jsp</title>
    </head>
    <body class="container">

    <jsp:include page="header.jsp"></jsp:include>
    <hr>
    <h1 class="text-center" style="color:orange">Page de connection </h1>
     <form:form method="post" action="/connection" modelAttribute="user"  class="mb-3" >

        <div class="form-group">
             <label class="form-label">Login</label> <form:input path="login" type="text" class="form-control custom-input"/>
             <form:errors path="login" cssClass="error"/>
        </div>

        <div class="form-group">
             <label class="form-label">Password</label> <form:input path="mdp" type="password" class="form-control custom-input"/>
              <form:errors path="mdp" cssClass="error"/>
        </div><br>

        <div class="form-group">
             <input type="submit" value="Submit" id="submit" >
        </div><br><br>

        <div>
         <a href="/newCompte" class="btn btn-success">CREER VOTRE COMPTE CLIENT</a>
        </div><br><br>

        <div>
         <a href="/modifierCompte" class="btn btn-danger">MODIFIER MON COMPTE</a>
        </div>
     </form:form><br>

       <style>
         .custom-input {
           width: 30%;
           }
         form{
            background-image: url('images/pexels-pixabay-45170.jpg');
            background-size: cover;
            background-repeat: no-repeat;
           }
          #submit{
            color:white;
            background-color:green;
           }
       </style>

    <jsp:include page="footer.jsp"></jsp:include>

