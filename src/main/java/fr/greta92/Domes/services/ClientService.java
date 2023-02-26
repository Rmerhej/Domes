    package fr.greta92.Domes.services;

    import fr.greta92.Domes.beans.Client;
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


        public Client updateClient(Client client){
            Client client1=new Client();
            client1.setNom_client(client.getNom_client());
            client1.setPrenom_client(client.getPrenom_client());
            client1.setTelephone_client(client.getTelephone_client());
            client1.setEmail_client(client.getEmail_client());
            client1.setLogin(client.getLogin());
            client1.setMdp(passwordEncoder.encode(client.getMdp()));
            clientRepository.save(client1);

            return client;
        }
        public  void deleteClient(Client client){
            clientRepository.delete(client);
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
        public boolean clientExist(Client client) throws Exception{
            if(clientRepository.existsByLoginAndMdp(client.getLogin(),client.getMdp())){
                return true;
            }
            return false;
        }
       public Client findClient(String login){
           Client client = clientRepository.findByLogin(login);
           return client ;

        }

    }

