    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.beans.User;
    import fr.greta92.Domes.repository.UserRepository;
    import fr.greta92.Domes.services.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;

    import javax.servlet.http.HttpSession;
    import javax.validation.Valid;
    @Controller
    public class ConnectionController {
            @Autowired
            private UserRepository userRepository;
    @Autowired
        UserService userService;
        @Autowired
        private HttpSession session;

        @PostMapping("/connection")
         public String verifyUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) throws Exception {
            System.out.println("ds VerifyUser");
            if(result.hasErrors()){
                return "seConnecter";
                }
            if(userService.userExist(user)==false){
                return "ConnectionErrorPage";
                }
         session.setAttribute("login",user.getLogin());
         session.setAttribute("mdp",user.getMdp());
          System.out.println("login :" +user.getLogin() +"\n"+ "mdp : " +user.getMdp());
          model.addAttribute("message","l'utilisateur est Connecté");
                return "index";
            }
    }
