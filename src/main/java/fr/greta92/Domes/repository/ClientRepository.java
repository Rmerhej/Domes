    package fr.greta92.Domes.repository;
    ;
    import fr.greta92.Domes.beans.Client;
    import org.springframework.data.repository.CrudRepository;

    public interface ClientRepository extends CrudRepository<Client, Integer> {
        boolean existsByLogin(String login);
        boolean existsByLoginAndMdp(String login, String mdp);
        Client findByLogin(String login);
        Client save(Client client);
        void deleteById(Integer id_client);
        /*
        * @Modifying
                @Query("UPDATE User u SET u.email = :newEmail WHERE u.id = :userId")
                int updateUserEmail(@Param("userId") Long userId, @Param("newEmail") String newEmail);

        *
        * */
    }
