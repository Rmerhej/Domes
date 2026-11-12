<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Onglets de l'espace admin. Avant l'include : <jsp:param name="actif" value="animaux|commandes"/> --%>
<nav aria-label="Espace administrateur">
  <ul class="puces mb-4">
    <li><a href="/admin" class="${param.actif == 'animaux' ? 'actif' : ''}" ${param.actif == 'animaux' ? 'aria-current="page"' : ''}><i class="bi bi-heart" aria-hidden="true"></i> Animaux</a></li>
    <li><a href="/admin/commandes" class="${param.actif == 'commandes' ? 'actif' : ''}" ${param.actif == 'commandes' ? 'aria-current="page"' : ''}><i class="bi bi-receipt" aria-hidden="true"></i> Commandes</a></li>
  </ul>
</nav>
