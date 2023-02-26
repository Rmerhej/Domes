package fr.greta92.Domes.controllers;
/*
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final SessionRepository<?> sessionRepository;

    public UserController(SessionRepository<?> sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping("/setConnected")
    public void setConnected(boolean connected) {
        Session session = sessionRepository.createSession();
        session.setAttribute("connected", connected);
    }

    @GetMapping("/getConnected")
    public boolean getConnected() {
        Session session = sessionRepository.createSession();
        Boolean connected = (Boolean) session.getAttribute("connected");
        return connected != null && connected;
    }
}
*/
