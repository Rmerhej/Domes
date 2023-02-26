    package fr.greta92.Domes.repository;
    ;
    import fr.greta92.Domes.beans.Client;
    import org.springframework.data.jpa.repository.JpaRepository;

    public interface ClientRepository extends JpaRepository<Client, Integer> {
        boolean existsByLogin(String login);
    }
