<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"><jsp:param name="titre" value="Commandes - Administration"/></jsp:include>

<div class="container section">
  <jsp:include page="_ongletsAdmin.jsp"><jsp:param name="actif" value="commandes"/></jsp:include>
  <h1 class="titre-page">Suivre les commandes</h1>

  <c:if test="${not empty succes}">
    <div class="alert alert-success" role="status"><c:out value="${succes}"/></div>
  </c:if>
  <c:if test="${not empty erreur}">
    <div class="alert alert-danger" role="alert"><c:out value="${erreur}"/></div>
  </c:if>

  <%-- Filtre par statut, avec le nombre de commandes de chaque statut --%>
  <ul class="puces mb-4" aria-label="Filtrer par statut">
    <li><a href="/admin/commandes" class="${empty filtre ? 'actif' : ''}" ${empty filtre ? 'aria-current="page"' : ''}>Toutes</a></li>
    <c:forEach var="s" items="${statuts}">
      <li><a href="/admin/commandes?statut=${s}" class="${filtre == s ? 'actif' : ''}" ${filtre == s ? 'aria-current="page"' : ''}>
        <c:out value="${s.libelle}"/> <span class="compteur">${nombres[s]}</span></a></li>
    </c:forEach>
  </ul>

  <c:choose>
    <c:when test="${empty commandes}">
      <div class="vide"><p>Aucune commande <c:if test="${not empty filtre}">« <c:out value="${filtre.libelle}"/> » </c:if>pour le moment.</p></div>
    </c:when>
    <c:otherwise>
      <div class="tableau table-responsive">
        <table class="table align-middle">
          <thead>
            <tr>
              <th scope="col">N°</th>
              <th scope="col">Date</th>
              <th scope="col">Client</th>
              <th scope="col">Animaux</th>
              <th scope="col" class="text-end">Total</th>
              <th scope="col">Statut</th>
              <th scope="col">Changer le statut</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="commande" items="${commandes}">
              <tr>
                <td><c:out value="${commande.id}"/></td>
                <td class="text-nowrap"><c:out value="${commande.dateFormatee}"/></td>
                <td>
                  <c:out value="${commande.client.prenom_client}"/> <c:out value="${commande.client.nom_client}"/>
                  <span class="d-block text-secondary small"><c:out value="${commande.client.email_client}"/></span>
                </td>
                <td>
                  <c:forEach var="ligne" items="${commande.lignes}" varStatus="etat">
                    <a href="/animal/${ligne.article.id_article}"><c:out value="${ligne.article.nom_article}"/></a><c:if test="${ligne.quantite > 1}"> ×&nbsp;${ligne.quantite}</c:if><c:if test="${not etat.last}">, </c:if>
                  </c:forEach>
                </td>
                <td class="text-end text-nowrap"><fmt:formatNumber value="${commande.montantTotal}" type="currency" currencyCode="EUR"/></td>
                <td><span class="etiquette statut-${commande.statut}"><c:out value="${commande.statut.libelle}"/></span></td>
                <td>
                  <c:choose>
                    <c:when test="${empty commande.statut.suivants}">
                      <span class="text-secondary small">Terminée</span>
                    </c:when>
                    <c:otherwise>
                      <%-- Seuls les statuts permis sont proposés (voir StatutCommande.getSuivants) --%>
                      <form action="/admin/commandes/${commande.id}/statut" method="post" class="d-flex gap-2"
                            onsubmit="return this.statut.value !== 'ANNULEE' || confirm('Annuler la commande n° ${commande.id} ? Ses animaux seront remis en vente.');">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <c:if test="${not empty filtre}"><input type="hidden" name="filtre" value="${filtre}"/></c:if>
                        <label class="visually-hidden" for="statut-${commande.id}">Nouveau statut de la commande ${commande.id}</label>
                        <select class="form-select form-select-sm" id="statut-${commande.id}" name="statut">
                          <c:forEach var="suivant" items="${commande.statut.suivants}">
                            <option value="${suivant}"><c:out value="${suivant.libelle}"/></option>
                          </c:forEach>
                        </select>
                        <button type="submit" class="btn btn-outline-primary btn-sm">Valider</button>
                      </form>
                    </c:otherwise>
                  </c:choose>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </c:otherwise>
  </c:choose>
</div>

<jsp:include page="footer.jsp"/>
