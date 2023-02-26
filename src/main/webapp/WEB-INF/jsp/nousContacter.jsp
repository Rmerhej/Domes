    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="fr">
    <head>
    <meta charset="UTF-8">
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
        crossorigin="anonymous">
    <title>nous contacter</title>
    </head>
    <body class="container">


    <jsp:include page="header.jsp"></jsp:include>
        <h1 class="text-center" style="color:orange">Nous Contacter</h1>
        <form action="#" method="post">
      <div class="mb-3">
        <label for="nom" class="form-label">NOM :</label>
        <input type="text" class="form-control" id="nom" name="nom">

      </div>

      <div class="mb-3">
        <label for="prenom" class="form-label">PRÉNOM :</label>
        <input type="text" class="form-control" id="prenom" name="prenom">

      </div>

      <div class="mb-3">
        <label for="mail" class="form-label">Email :</label>
        <input type="email" class="form-control" id="mail" name="mail">

      </div>
      <div class="form-floating">
      <textarea class="form-control" placeholder="VOTRE MESSAGE" id="floatingTextarea"></textarea>
      <label for="floatingTextarea">Votre Message</label>
    </div><br>


      <button type="submit" class="btn btn-primary">Submit</button><hr>
    <jsp:include page="footer.jsp"></jsp:include>

    </form>
    </body>
    </html>