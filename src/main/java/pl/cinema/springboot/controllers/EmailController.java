package pl.cinema.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.cinema.springboot.mail.EmailSender;

import javax.activation.DataSource;

@Controller
public class EmailController {

    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine){
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @RequestMapping("/api/send")
    @ResponseBody
    public String send(String sendTo, DataSource confirm) {
        Context context = new Context();
        context.setVariable("header", "Potwierdzenie dokonania zakupy na CinemaApp");
        context.setVariable("title", "Dziękujemy za zaufanie naszej firmie!");
        context.setVariable("description", "W załączniku znajdziesz plik z potwierdzeniem zakupu. \n" +
                "Plik pdf możesz również pobrać z naszej stronie po zalogowaniu. \n" +
                "Liczymy, że jeszcze do nas wpadniesz! Miłego seansu :)");
        String body = null;
        try {
            body = templateEngine.process("template.html", context);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        emailSender.sendEmail(sendTo, "CinemaApp - potwierdzenie zakupu", body, confirm);
        return "index";
    }
}
