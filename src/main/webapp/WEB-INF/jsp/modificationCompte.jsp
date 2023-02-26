    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
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
    <form action="#" method="post">
    <div class="mb-3">
    <label class="form-label" for="nom">NOM :</label>
    <input class="form-control" type="text" id="nom" name="nom">
    </div>
    <div>
    <label class="form-label"  for="prenom">PRENOM :</label>
    <input class="form-control" type="text" id="prenom" name="prenom">
    </div>
    <div class="mb-3">
    <label class="form-label"  for="tel">TÉLÉPHONE :</label>
    <input class="form-control" type="tel" id="tel" name="tel">
    </div>
    <div class="mb-3">
    <label class="form-label"  for="mail">EMAIL :</label>
    <input class="form-control" type="email" id="mail" name="mail">
    </div>
    <div class="mb-3">
    <label class="form-label"  for="adresse">ADRESSE :</label>
    <input class="form-control" type="text" id="adresse" name="adresse">
    </div>
    <div class="mb-3">
    <button type="submit" class="btn btn-primary" >MODIFIER</button>
    </div><hr>
    <jsp:include page="footer.jsp"></jsp:include>



    </form>
    </body>
    </html>