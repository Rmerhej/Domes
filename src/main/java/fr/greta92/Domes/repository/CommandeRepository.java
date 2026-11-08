package fr.greta92.Domes.repository;

import fr.greta92.Domes.entities.Commande;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommandeRepository extends CrudRepository<Commande, Integer> {

    /*
     * "join fetch" : les lignes (et leurs articles) sont chargées dans la MÊME
     * requête que la commande. Sans ça, elles sont chargées "plus tard" (lazy),
     * au moment où la JSP les lit... alors que la connexion à la base est
     * déjà fermée -> LazyInitializationException.
     *
     * "distinct" : la jointure renvoie une ligne SQL par LigneCommande ;
     * distinct évite d'avoir la même commande plusieurs fois dans la liste.
     */

    @Query("select distinct c from Commande c "
         + "left join fetch c.lignes l left join fetch l.article "
         + "where c.id = :id")
    Optional<Commande> findAvecLignes(@Param("id") int id);

    @Query("select distinct c from Commande c "
         + "left join fetch c.lignes l left join fetch l.article "
         + "where c.client.id_client = :idClient "
         + "order by c.dateCommande desc")
    List<Commande> findAvecLignesParClient(@Param("idClient") int idClient);
}
