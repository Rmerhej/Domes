     <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
     <script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
     <jsp:include page="header.jsp"></jsp:include>

     <h1  class="text-center" style="color: orange">CATALOGUE</h1><hr>
     <div class="container">
       <div class="row">
         <c:forEach items="${articles}" var="article">
           <div class="col-md-3">
             <div class="card">
               <img src="<c:url value="${article.image_article}" />" width="255" height="168"   class="card-img-top" alt="#"/>

               <div class="card-body">
                 <h5 class="card-title"><c:out value="${article.nom_article}" /></h5>
                 <p class="card-text">Prix :<c:out value="${article.prix_unitaire_article}"/></p>
                  <form action="/panier" method="post" modelAttribute="idPrPanier" >
                        <input type="hidden" name="idArticle" value="<c:out value="${article.id_article}"/>" />
                        <label>Quantité :</label>
                        <input type="number" name="quantite" value="1" min="1"><br><br>
                        <button type="submit"  class="btn btn-primary text-center">Ajouter au Panier</button>

                 </form>
               </div>
             </div>
           </div>
         </c:forEach>
       </div>
     </div><br><br>


        <h3 class="text-center" style="color: orange">Notre Sélection</h3>

        <!-- Articles -->
        <div class="d-flex justify-content-between">

            <div class="card" style="width: 18rem;">
                <img src="images/Rottweiler.jpg" class="card-img-top" alt="#">
                <div class="card-body">
                    <h5 class="card-title">Rottweiler</h5>
                    <p class="card-text">Le Rottweiler est une race de chien de travail d'origine allemande. Il est connu pour sa force physique, son intelligence et sa loyauté envers son propriétaire. Les Rottweilers peuvent être protecteurs et peuvent avoir un comportement agressif envers les étrangers sans formation et socialisation adéquates. Ils ont besoin d'une formation ferme et d'exercice physique quotidien pour dépenser leur énergie. Les Rottweilers sont souvent utilisés en tant que chiens de sécurité, de police et de travail</p>
                    <a href="/panier" class="btn btn-primary">Ajouter au Panier</a>

                </div>
            </div>
            <div class="card" style="width: 18rem;">
                <img src="images/Persian.jpg"
                    class="card-img-top" alt="#">
                <div class="card-body">
                    <h5 class="card-title">Chat Persan</h5>
                    <p class="card-text">Le chat Persan est une race de chat populaire, reconnue pour son pelage long et doux et son apparence royale. Les chats Persans ont des traits de visage sphériques et une fourrure abondante qui les rendent très photogéniques. Ils sont souvent considérés comme des animaux de compagnie très affectueux et doux, mais peuvent aussi être assez calmes et indépendants. Les chats Persans ont besoin de beaucoup de soins pour maintenir leur pelage long et doux, ce qui implique souvent un brossage régulier et une toilette occasionnelle.</p>
                    <a href="/panier" class="btn btn-primary">Ajouter au Panier</a>

                </div>
            </div>
            <div class="card" style="width: 18rem;">
                <img src="images/Angelfish.jpg"
                    class="card-img-top" alt="#" width="286" height="214">
                <div class="card-body">
                    <h5 class="card-title">Angel fish</h5>
                    <p class="card-text">L'Ange est un poisson tropical populaire utilisé en aquariophilie. Il a une forme de corps allongé et une nageoire dorsale développée qui lui donne une apparence royale. Les anguilles ont souvent des couleurs vives, telles que le noir, le bleu et le jaune, qui peuvent varier selon la variété. Les anguilles sont souvent considérées comme des poissons pacifiques et sociables, mais peuvent être agressives envers les poissons plus petits ou les membres de leur propre espèce. Les anguilles ont besoin d'un espace de nage adéquat et d'un environnement bien équilibré pour maintenir une bonne santé.</p>
                    <a href="/panier" class="btn btn-primary">Ajouter au Panier</a>

                </div>
            </div>
        </div>
        <br>
        <br>
    <jsp:include page="footer.jsp"></jsp:include>