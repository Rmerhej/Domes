<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"><jsp:param name="titre" value="${article.nouveau ? 'Ajouter un animal' : 'Modifier un animal'}"/></jsp:include>

<div class="container section">
  <p class="mb-2"><a href="/admin">Retour à la liste</a></p>

  <div class="carte-formulaire" style="max-width: 760px">
    <h1 class="titre-page">${article.nouveau ? 'Ajouter un animal' : 'Modifier un animal'}</h1>

    <c:if test="${not empty erreur}">
      <div class="alert alert-danger" role="alert"><c:out value="${erreur}"/></div>
    </c:if>

    <%-- <form:form> ajoute le jeton CSRF et réaffiche les valeurs saisies --%>
    <form:form method="post" action="/admin/articles/enregistrer" modelAttribute="article" enctype="multipart/form-data">
      <form:hidden path="id"/>
      <div class="row g-3">
        <div class="col-sm-7">
          <label class="form-label" for="nom">Nom</label>
          <form:input path="nom" id="nom" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="60"/>
          <form:errors path="nom" cssClass="invalid-feedback d-block"/>
        </div>
        <div class="col-sm-5">
          <label class="form-label" for="prix">Prix (€)</label>
          <form:input path="prix" id="prix" type="number" step="0.01" min="0.01" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
          <form:errors path="prix" cssClass="invalid-feedback d-block"/>
        </div>

        <div class="col-12">
          <label class="form-label" for="idProduit">Race</label>
          <form:select path="idProduit" id="idProduit" cssClass="form-select" cssErrorClass="form-select is-invalid">
            <form:option value="" label="Choisir une race"/>
            <c:forEach var="groupe" items="${races}">
              <optgroup label="<c:out value="${groupe.key}"/>">
                <c:forEach var="race" items="${groupe.value}">
                  <form:option value="${race.id_produit}" label="${race.nom_produit}"/>
                </c:forEach>
              </optgroup>
            </c:forEach>
          </form:select>
          <div class="form-text">L'espèce (chiens, chats...) découle de la race.</div>
          <form:errors path="idProduit" cssClass="invalid-feedback d-block"/>
        </div>

        <div class="col-sm-7">
          <label class="form-label" for="fichierPhoto">Envoyer une photo</label>
          <input class="form-control" type="file" id="fichierPhoto" name="fichierPhoto"
                 accept="image/jpeg,image/png,image/webp" aria-describedby="aide-envoi">
          <div class="form-text" id="aide-envoi">JPEG, PNG ou WebP, 5 Mo maximum.</div>
          <div class="invalid-feedback d-block" id="erreur-envoi" role="alert"></div>

          <label class="form-label mt-3" for="image">Ou choisir une photo déjà disponible</label>
          <form:input path="image" id="image" list="photos" cssClass="form-control" cssErrorClass="form-control is-invalid"
                      placeholder="images/Mon-animal.jpg" aria-describedby="aide-photo"/>
          <datalist id="photos">
            <c:forEach var="photo" items="${photos}"><option value="<c:out value="${photo}"/>"></c:forEach>
          </datalist>
          <div class="form-text" id="aide-photo">Cliquez dans le champ pour voir la liste. Une photo envoyée est prioritaire.</div>
          <form:errors path="image" cssClass="invalid-feedback d-block"/>
        </div>
        <div class="col-sm-5">
          <span class="form-label d-block">Aperçu</span>
          <img id="apercu" class="apercu-image" alt="" src="<c:if test="${not empty article.image}"><c:url value="/${article.image}"/></c:if>">
        </div>

        <div class="col-12">
          <label class="form-label" for="description">Description <span class="text-secondary fw-normal">(facultatif)</span></label>
          <form:textarea path="description" id="description" rows="5" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="2000"/>
          <form:errors path="description" cssClass="invalid-feedback d-block"/>
        </div>
      </div>

      <div class="d-flex flex-wrap gap-3 mt-4">
        <button type="submit" class="btn btn-primary">${article.nouveau ? 'Ajouter l\'animal' : 'Enregistrer les modifications'}</button>
        <a href="/admin" class="btn btn-link">Annuler</a>
      </div>
    </form:form>
  </div>
</div>

<script>
  // Aperçu de la photo, et refus immédiat d'un fichier trop lourd
  (function () {
    var TAILLE_MAX = 5 * 1024 * 1024;
    var champ = document.getElementById('image'),
        fichier = document.getElementById('fichierPhoto'),
        erreur = document.getElementById('erreur-envoi'),
        apercu = document.getElementById('apercu'),
        url = null;

    champ.addEventListener('input', function () {
      if (fichier.files.length) { return; }   // la photo envoyée reste prioritaire
      var valeur = champ.value.trim();
      apercu.src = valeur ? '/' + valeur.replace(/^\/+/, '') : '';
    });

    fichier.addEventListener('change', function () {
      erreur.textContent = '';
      if (url) { URL.revokeObjectURL(url); url = null; }
      var f = fichier.files[0];
      if (!f) { return; }
      if (f.size > TAILLE_MAX) {
        erreur.textContent = 'Cette photo dépasse 5 Mo. Choisissez une image plus légère.';
        fichier.value = '';
        return;
      }
      url = URL.createObjectURL(f);
      apercu.src = url;
    });
  })();
</script>

<jsp:include page="footer.jsp"/>
