package fr.greta92.Domes.repository;

import fr.greta92.Domes.entities.Client;
import fr.greta92.Domes.entities.Commande;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommandeRepository extends CrudRepository<Commande, Integer> {

    // Requête générée par Spring Data à partir du nom de la méthode :
    // SELECT c FROM Commande c WHERE c.client = ?1 ORDER BY c.dateCommande DESC
    List<Commande> findByClientOrderByDateCommandeDesc(Client client);
}
