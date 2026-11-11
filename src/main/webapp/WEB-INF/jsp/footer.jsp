<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Fin commune de toutes les pages : ferme <main>, pied de page, scripts. --%>
</main>

<footer class="pied">
  <div class="container">
    <div class="row g-4">
      <div class="col-md-5">
        <h2>Domes</h2>
        <p class="mb-0">Chiens, chats, poissons, reptiles et oiseaux : trouvez le compagnon qui vous ressemble.</p>
      </div>
      <div class="col-6 col-md-3">
        <h2>Aide</h2>
        <ul>
          <li><a href="/nousContacter">Nous contacter</a></li>
          <li><a href="/mesCommandes">Mes commandes</a></li>
          <li><a href="/panier">Mon panier</a></li>
        </ul>
      </div>
      <div class="col-6 col-md-4">
        <h2>Informations</h2>
        <ul>
          <li><a href="/cgv">Conditions générales de vente</a></li>
          <li><a href="/mentionsLegales">Mentions légales</a></li>
        </ul>
      </div>
    </div>
    <p class="mentions mb-0">Projet de formation : les paiements sont simulés, aucun animal n'est réellement vendu.</p>
  </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
<script>
  // Bouton « afficher / masquer » ajouté à chaque champ mot de passe du site
  // (connexion, inscription, Mon compte) : pas besoin de le recopier dans chaque page.
  document.querySelectorAll('input[type="password"]').forEach(function (champ) {
    var groupe = document.createElement('div');
    groupe.className = 'input-group';
    champ.parentNode.insertBefore(groupe, champ);
    groupe.appendChild(champ);

    var bouton = document.createElement('button');
    bouton.type = 'button';                      // ne doit pas envoyer le formulaire
    bouton.className = 'btn btn-outline-secondary bouton-oeil';
    if (champ.id) { bouton.setAttribute('aria-controls', champ.id); }
    groupe.appendChild(bouton);

    function afficher(enClair) {
      champ.type = enClair ? 'text' : 'password';
      var libelle = enClair ? 'Masquer le mot de passe' : 'Afficher le mot de passe';
      bouton.setAttribute('aria-pressed', enClair ? 'true' : 'false');
      bouton.setAttribute('aria-label', libelle);
      bouton.title = libelle;
      bouton.innerHTML = '<i class="bi ' + (enClair ? 'bi-eye-slash' : 'bi-eye') + '" aria-hidden="true"></i>';
    }
    afficher(false);

    bouton.addEventListener('click', function () {
      afficher(champ.type === 'password');
      champ.focus();
    });
    // Toujours remasquer à l'envoi : le navigateur ne propose d'enregistrer
    // un mot de passe que si le champ est de type "password"
    if (champ.form) {
      champ.form.addEventListener('submit', function () { afficher(false); });
    }
  });
</script>
</body>
</html>
