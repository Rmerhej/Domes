   <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!DOCTYPE html>

  <html lang="fr">
    <head>
      <meta charset="UTF-8">
      <title>index.jsp</title>
       <link
         href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
         rel="stylesheet"
         integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
         crossorigin="anonymous">
       <link rel="stylesheet" href="/css/index.css"/>
    </head>
    <body class="container">
        <!-- HEADER -->
        <br>
        <div class="container text-center">
            <div class="row align-items-start">
                <div class="col">
                    <img alt="logo domesServices" src="images/logo_DOMES_SERVICE.png"
                        height="50">
                </div>
                <div class="col">
                    <div class="btn-group">
                            <button class="btn dropdown-toggle" type="button"
                            data-bs-toggle="dropdown" data-bs-auto-close="true"
                            aria-expanded="false">CATÉGORIES</button>
                        <ul class="dropdown-menu">
                            <li><form action="/tocateg?name=1" method="post"><button type="submit" class="dropdown-item"> CHIENS </button></form></li>
                            <li><form action="/tocateg?name=2" method="post"><button type="submit" class="dropdown-item"> CHATS </button></form></li>
                            <li><form action="/tocateg?name=4" method="post"><button type="submit" class="dropdown-item"> POISSONS </button></form></li>
                            <li><form action="/tocateg?name=5" method="post"><button type="submit" class="dropdown-item"> REPTILES </button></form></li>
                            <li><form action="/tocateg?name=6" method="post"><button type="submit" class="dropdown-item"> OISEAUX </button></form></li>
                        </ul>
                    </div>
               </div>
               <div class="col">
                    <form action="/search" method="post"  class="d-flex">
                      <div>
                        <input type="text" id="nom" name="search" path="articleRecherche" class="form-control me-2" placeholder="Rechercher"/>
                      </div>
                      <button type="submit" class="btn btn-outline-success" >Go</button>
                    </form>
               </div>
               <div class="col">

                   <a href="/seConnecter">
                      <img alt="représentation personne" src="images/homme.png" height="25" width="25"> </a>

                    <a href="/panier">
                      <img src="images/bitcoin.png" height="25" width="25" alt="panier"></a>

                   <a href="/accueil"> <img src="images/home.svg" height="25" width="25" alt="maison"> </a>
               </div>
            </div>
        </div>
        <br>
        <!-- header -->

        <img src="images/pexels-katarzyna-modrzejewska-1314550.jpg" class="img-fluid" alt="chat" width=100%><br><br>

        <!-- CATÉGORIES -->
        <div>${message}</div>
        <h2 class="text-center" style="color: orange">CATÉGORIES</h2>
        <hr>
        <div class="d-flex justify-content-between">

            <div>
                <form action="/tocateg?name=4" method="post"><button type="submit">
                  <img alt="poisson" class="rounded" id="img1Index" src="images/Goldfish.jpg"> </button>
                </form>
                <p style="color: orange">poissons</p>
            </div>
            <div>
               <form action="/tocateg?name=2" method="post"><button type="submit">
                  <img alt="chat" class="rounded" id="img2Index" src="images/the-red-or-white-cat-i-on-white-studio.jpg"> </button>
               </form>
                <p style="color: orange">chats</p>
            </div>
            <div>
               <form action="/tocateg?name=1" method="post"><button type="submit">
                   <img alt="chien" class="rounded" id="img3Index" width="75" height="75" src="images/pexels-chevanon-photography-1108099.jpg"> </button>
               </form>
                <p style="color: orange">chiens</p>
            </div>
            <div>
              <form action="/tocateg?name=6" method="post"><button type="submit" >
                 <img alt="oiseau" class="rounded" id="img4Index"src="images/closeup-shot-of-male-hawfinch-sitting-on-branch.jpg"> </button>
              </form>
                <p style="color: orange">oiseaux</p>
            </div>
            <div>
            <form action="/tocateg?name=5" method="post"><button type="submit">
              <img alt="reptil" class="rounded" id="img5Index" src="images/green-lizard-on-branch-green-lizard-sunbathing-on-branch-green-lizard-climb-on-wood.jpg"
                 height="75" width="75"> </button>
            </form>
                <p style="color: orange">reptiles</p>
            </div>
        </div>   <br> <br>

        <!-- Articles -->
        <div class="d-flex justify-content-between">
            <div class="card" style="width: 18rem;">
                <img src="images/pexels-chevanon-photography-1108099.jpg"
                    class="card-img-top" alt="#">
                <div class="card-body">
                    <h5 class="card-title">Chien</h5>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card</p>
                    <form action="/catalogue" method="post">
                       <button type="submit"  class="btn btn-primary">CONSULTER LE CATALOGUE</button>
                    </form>
                </div>
            </div>
            <div class="card" style="width: 18rem;">
                <img src="images/pexels-richard-segal-1618606.jpg" class="card-img-top" alt="#">
                <div class="card-body">
                    <h5 class="card-title">Tortue</h5>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card</p>
                  <form action="/catalogue" method="post">
                     <button type="submit"  class="btn btn-primary">CONSULTER LE CATALOGUE</button>
                  </form>
                </div>
            </div>
            <div class="card" style="width: 18rem;">
                <img src="images/pexels-roshan-kamath-1661179.jpg"
                    class="card-img-top" alt="#" width="286" height="214">
                <div class="card-body">
                    <h5 class="card-title">Perroquet</h5>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card</p>
                  <form action="/catalogue" method="post">
                      <button type="submit"  class="btn btn-primary">CONSULTER LE CATALOGUE</button>
                   </form>
                </div>
            </div>
        </div>  <br><br>
        <div>
            <a href="/modifierCompte" class="btn btn-danger">MODIFIER MON COMPTE</a>
         </div>
        <div>
           <a href="/newCompte" class="btn btn-success">CREER VOTRE COMPTE CLIENT</a>
        </div><br><br>

        <jsp:include page="footer.jsp"></jsp:include>

        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
    </body>
    </html>