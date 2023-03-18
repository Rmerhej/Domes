package fr.greta92.Domes.controllers;

import fr.greta92.Domes.beans.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;


@Controller
public class DeconnectionController {
   @Autowired
    HttpSession session;
    @GetMapping("/deconnection")
    public String deconnecterClient(Client client, Model model) throws Exception {
        String dejaConnecte = (String) session.getAttribute("status");
        session.setAttribute("status","deconnecté");
        model.addAttribute("deconnecte","vous êtes  déconnecté");
        model.addAttribute("dec", true);
        System.out.println(dejaConnecte);
    return "index";
}
}
