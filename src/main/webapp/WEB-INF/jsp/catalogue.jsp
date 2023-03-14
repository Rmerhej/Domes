     <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
     <script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
     <jsp:include page="header.jsp"></jsp:include>

     <h1  class="text-center" style="color: orange">CATALOGUE</h1><hr>

     <table class="table">
       <thead>
         <tr>
           <th>Article</th>
           <th>Prix</th>
           <th>Image</th>
         </tr>
       </thead>

       <tbody>
         <c:forEach var="article" items="${articles}">
          <tr>
           <td><c:out value="${article.nom_article}" /></td>
           <td><c:out value="${article.prix_unitaire_article}"/></td>
           <td><img src="<c:url value="${article.image_article}"  />" height="75" width="75"/></td>
           <td>
              <form action="/panier" method="post" modelAttribute="idPrPanier" >
                <input type="hidden" name="idArticle" value="<c:out value="${article.id_article}"/>" />
                <button type="submit"  class="btn btn-primary">Ajouter au Panier</button>
              </form>
           </td>
           <td><a href="/achat" class="btn btn-success">Acheter</a></td>
          </tr>
         </c:forEach>
       </tbody>

    </table>

    <!-- CATÉGORIES -->
        <h2 class="text-center" style="color: orange">VEUILLEZ CHOISIR UNE CATÉGORIE</h2>
        <hr>
        <div class="d-flex justify-content-between">

            <div>
                <a href="/poissons"><img alt="poisson" class="rounded" id="img1Index"
                    src="images/fighting-fish-on-black-background.jpg"></a>
                <p style="color: orange">poissons</p>
            </div>
            <div>
                <a href="/chats"><img alt="chat" class="rounded" id="img2Index"
                    src="images/the-red-or-white-cat-i-on-white-studio.jpg"> </a>
                <p style="color: orange">chats</p>
            </div>
            <div>
                <a href="/chiens"><img alt="chien" class="rounded" id="img3Index" width="75" height="75"
                    src="images/pexels-chevanon-photography-1108099.jpg"></a>
                <p style="color: orange">chiens</p>
            </div>
            <div>
                <a href="/oiseaux"><img alt="oiseau" class="rounded" id="img4Index"
                    src="images/closeup-shot-of-male-hawfinch-sitting-on-branch.jpg"></a>
                <p style="color: orange">oiseaux</p>
            </div>
            <div>
                <a href="/reptiles"><img alt="reptil" class="rounded" id="img5Index"
                    src="images/green-lizard-on-branch-green-lizard-sunbathing-on-branch-green-lizard-climb-on-wood.jpg"
                    height="75" width="75"></a>
                <p style="color: orange">reptiles</p>
            </div>

        </div><hr>
        <br> <br>

        <h3 class="text-center" style="color: orange">Notre Sélection</h3>

        <!-- Articles -->
        <div class="d-flex justify-content-between">

            <div class="card" style="width: 18rem;">
                <img src="images/Rottweiler.jpg"
                    class="card-img-top" alt="#">
                <div class="card-body">
                    <h5 class="card-title">Rottweiler</h5>
                    <p class="card-text">Le Rottweiler est une race de chien de travail d'origine allemande. Il est connu pour sa force physique, son intelligence et sa loyauté envers son propriétaire. Les Rottweilers peuvent être protecteurs et peuvent avoir un comportement agressif envers les étrangers sans formation et socialisation adéquates. Ils ont besoin d'une formation ferme et d'exercice physique quotidien pour dépenser leur énergie. Les Rottweilers sont souvent utilisés en tant que chiens de sécurité, de police et de travail</p>
                    <a href="/panier" class="btn btn-primary">Ajouter au Panier</a>
                        <a href="/achat" class="btn btn-success">Acheter</a>
                </div>
            </div>
            <div class="card" style="width: 18rem;">
                <img src="images/Persian.jpg"
                    class="card-img-top" alt="#">
                <div class="card-body">
                    <h5 class="card-title">Chat Persan</h5>
                    <p class="card-text">Le chat Persan est une race de chat populaire, reconnue pour son pelage long et doux et son apparence royale. Les chats Persans ont des traits de visage sphériques et une fourrure abondante qui les rendent très photogéniques. Ils sont souvent considérés comme des animaux de compagnie très affectueux et doux, mais peuvent aussi être assez calmes et indépendants. Les chats Persans ont besoin de beaucoup de soins pour maintenir leur pelage long et doux, ce qui implique souvent un brossage régulier et une toilette occasionnelle.</p>
                    <a href="/panier" class="btn btn-primary">Ajouter au Panier</a>
                        <a href="/achat" class="btn btn-success">Acheter</a>
                </div>
            </div>
            <div class="card" style="width: 18rem;">
                <img src="images/Angelfish.jpg"
                    class="card-img-top" alt="#" width="286" height="214">
                <div class="card-body">
                    <h5 class="card-title">Angel fish</h5>
                    <p class="card-text">L'Ange est un poisson tropical populaire utilisé en aquariophilie. Il a une forme de corps allongé et une nageoire dorsale développée qui lui donne une apparence royale. Les anguilles ont souvent des couleurs vives, telles que le noir, le bleu et le jaune, qui peuvent varier selon la variété. Les anguilles sont souvent considérées comme des poissons pacifiques et sociables, mais peuvent être agressives envers les poissons plus petits ou les membres de leur propre espèce. Les anguilles ont besoin d'un espace de nage adéquat et d'un environnement bien équilibré pour maintenir une bonne santé.</p>
                    <a href="/panier" class="btn btn-primary">Ajouter au Panier</a>
                        <a href="/achat" class="btn btn-success">Acheter</a>
                </div>
            </div>
        </div>
        <br>
        <br>
    <jsp:include page="footer.jsp"></jsp:include>