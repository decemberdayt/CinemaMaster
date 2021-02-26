package pl.cinema.springboot.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import javax.activation.DataSource;
import org.thymeleaf.TemplateEngine;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderImpl implements EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    public EmailSenderImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(String to, String title, DataSource confirm) {

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

        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo("cinemaAppNoReply@cinemaapp.pl");
            helper.setFrom("cinemaAppNoReply@cinemaapp.pl");
            helper.setSubject(title);
            helper.setText(body, true);
            helper.addAttachment("confirmation.pdf", confirm);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mail);
    }
}
