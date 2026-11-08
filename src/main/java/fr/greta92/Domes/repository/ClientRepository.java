    package fr.greta92.Domes.repository;
    ;
    import fr.greta92.Domes.entities.Client;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface ClientRepository extends CrudRepository<Client, Integer> {
        boolean existsByLogin(String login);
        boolean existsByLoginAndMdp(String login, String mdp);
        Client findByLogin(String login);
        Client save(Client client);
        void deleteById(Integer id_client);

    }
