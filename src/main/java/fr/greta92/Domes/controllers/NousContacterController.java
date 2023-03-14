    package fr.greta92.Domes.controllers;

    import fr.greta92.Domes.services.EmailService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.mail.javamail.JavaMailSender;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;

    import javax.mail.MessagingException;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;

    @Controller
    public class NousContacterController {
       @Autowired
        EmailService emailService;
        @RequestMapping(value = "/nousContacter", method = RequestMethod.POST)
        public String contact(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
            model.addAttribute("Message","Votre message à été envoyé!");
            String nom= request.getParameter("nom");
            String prenom= request.getParameter("prenom");
            String mail= request.getParameter("mail");
            String msg=request.getParameter("msg");
            System.out.println("nom :"+nom+"\n"+"prenom :"+prenom+"\n"+"mail :"+mail+"\n"+msg);
            boolean userMessage=true;
            try{
                String to ="rolandmerhej7@gmail.com";
                String subject= "message de :"+nom+" "+prenom;
                String text = "from :"+mail+"\n"+msg;
                emailService.sendSimpleMessage(to,subject,text);
                return "nousContacter";
            }
            catch(MessagingException e){
                return e.getMessage();
            }


        }

    }
