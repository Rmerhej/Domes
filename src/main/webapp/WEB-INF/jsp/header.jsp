    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html lang="fr">
    <head>
     <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
        crossorigin="anonymous">
    <title>header</title>
    </head>
    <body class="container">

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

                <a href="/seConnecter" style="margin-right: 15px;"><img alt="représentation personne" src="images/homme.png"height="25" width="25"></a>
                <a href="/panier" class="position-relative" title="Mon panier">
                    <img src="images/bitcoin.png" height="25" width="25" alt="panier">
                    <c:if test="${sessionScope.cartCount > 0}">
                        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                            <c:out value="${sessionScope.cartCount}"/>
                        </span>
                    </c:if>
                </a>
                <a href="/accueil" style="margin-left: 15px;"><img src="images/home.svg" height="25" width="25" alt="maison"></a>
                <a href="/deconnection"> <img src="images/logout.png" height="25" width="25" alt="déconnexion"> </a>

              </div>
            </div>
        </div>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous">
        </script>
