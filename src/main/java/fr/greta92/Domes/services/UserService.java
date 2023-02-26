    package fr.greta92.Domes.services;

    import fr.greta92.Domes.beans.User;
    import fr.greta92.Domes.repository.UserRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    @Service
    public class UserService {
        @Autowired
        UserRepository userRepository;
        public boolean userExist(User user) throws Exception{
            if(userRepository.existsByLoginAndMdp(user.getLogin(),user.getMdp())){
                return true;
            }
            return false;
        }

    }
