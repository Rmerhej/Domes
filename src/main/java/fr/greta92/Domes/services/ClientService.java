    package fr.greta92.Domes.services;

    import fr.greta92.Domes.beans.Client;
    import fr.greta92.Domes.repository.ClientRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    @Service
    public class ClientService {
        @Autowired
        private ClientRepository clientRepository;
        @Autowired
        private PasswordEncoder passwordEncoder;

        public String createClient(Client client) throws Exception {
            if (clientRepository.existsByLogin(client.getLogin())) {
                //throw new Exception("Un Client avec ce login existe déjà");
    return "errorPage";
            }

            client.setMdp_client(passwordEncoder.encode(client.getMdp_client()));
            System.out.println("just before save");
        clientRepository.save(client);
            return "newCompte";
        }
    }

