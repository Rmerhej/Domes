    package fr.greta92.Domes.repository;

    import fr.greta92.Domes.beans.User;
    import org.springframework.data.repository.CrudRepository;


    public interface UserRepository extends CrudRepository<User,Integer> {

    boolean existsByLoginAndMdp(String login, String mdp);
    }
