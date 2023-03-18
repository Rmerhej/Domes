    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <!DOCTYPE html>

    <html lang="fr">
       <head>
         <meta charset="UTF-8">
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
         <title>modifierCoordonnees.jsp</title>
      </head>

      <body class="container">

      <jsp:include page="header.jsp"></jsp:include>
        <h1 class="text-center" style="color:orange">Modifier Mes Coordonnées.</h1>

        <form action="/modification" method="post" >

          <div class="mb-3">
            <label class="form-label" for="nom">NOM :</label>
            <input class="form-control" type="text" id="nom" name="nom_client" value=${clientConnecteSession.nom_client}>
          </div>

          <div>
            <label class="form-label"  for="prenom">PRENOM :</label>
            <input class="form-control" type="text" id="prenom" name="prenom_client" value=${clientConnecteSession.prenom_client}>
          </div>

          <div class="mb-3">
            <label class="form-label"  for="tel">TÉLÉPHONE :</label>
            <input class="form-control" type="tel" id="tel" name="telephone_client" value=${clientConnecteSession.telephone_client}>
          </div>

          <div class="mb-3">
            <label class="form-label"  for="mail">EMAIL :</label>
            <input class="form-control" type="email" id="mail" name="email_client" value=${clientConnecteSession.email_client}>
          </div>

          <div class="mb-3">
            <label class="form-label"  for="login">LOGIN :</label>
            <input class="form-control" type="text" id="login"  name="login"  value=${clientConnecteSession.login}>
          </div>

          <div class="mb-3">
            <label class="form-label"  for="mdp">MOT DE PASSE :</label>
            <input class="form-control" type="text" id="mdp" name="mdp" value=${clientConnecteSession.mdp}>
          </div>

          <div class="mb-3">
             <button type="submit" class="btn btn-primary" >MODIFIER</button>
          </div><hr>
        </form>
      </body>
   </html>