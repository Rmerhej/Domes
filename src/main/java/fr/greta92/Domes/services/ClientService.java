    package fr.greta92.Domes.services;

    import fr.greta92.Domes.entities.Client;
    import fr.greta92.Domes.repository.ClientRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import javax.servlet.http.HttpSession;

    @Service
    public class ClientService {
        @Autowired
        private ClientRepository clientRepository;
        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired
        HttpSession session;


        /**
         * Met à jour le compte d'un client existant (même id, pas de suppression).
         *
         * @param idClient   id du client connecté
         * @param formulaire valeurs saisies dans le formulaire
         * @param nouveauMdp nouveau mot de passe, ou vide pour garder l'actuel
         * @return le client enregistré
         * @throws IllegalArgumentException si le login est déjà pris par un autre client
         */
        public Client updateClient(int idClient, Client formulaire, String nouveauMdp){
            Client client = clientRepository.findById(idClient)
                    .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"));

            Client autre = clientRepository.findByLogin(formulaire.getLogin());
            if (autre != null && autre.getId_client() != idClient) {
                throw new IllegalArgumentException("Ce login est déjà utilisé, veuillez en choisir un autre");
            }

            client.setNom_client(formulaire.getNom_client());
            client.setPrenom_client(formulaire.getPrenom_client());
            client.setTelephone_client(formulaire.getTelephone_client());
            client.setEmail_client(formulaire.getEmail_client());
            client.setLogin(formulaire.getLogin());
            // On ne re-hache que si un nouveau mot de passe a été saisi
            if (nouveauMdp != null && !nouveauMdp.isBlank()) {
                client.setMdp(passwordEncoder.encode(nouveauMdp));
            }
            return clientRepository.save(client);
        }

        public String createClient(Client client) throws Exception {
            if (clientRepository.existsByLogin(client.getLogin())) {
               return "errorPage";
            }

            client.setMdp(passwordEncoder.encode(client.getMdp()));
            System.out.println("just before save");
            clientRepository.save(client);
               return "newCompte";
        }
        /**
         * Vérifie le couple login / mot de passe.
         * Le mot de passe est stocké haché (BCrypt) : on ne peut pas le comparer en SQL,
         * il faut récupérer le client puis utiliser passwordEncoder.matches().
         */
        public boolean clientExist(Client client) throws Exception{
            if (client.getLogin() == null || client.getMdp() == null) {
                return false;
            }
            Client enBase = clientRepository.findByLogin(client.getLogin());
            return enBase != null && passwordEncoder.matches(client.getMdp(), enBase.getMdp());
        }
       public Client findClient(String login){
           Client client = clientRepository.findByLogin(login);
           return client ;

        }

    }

