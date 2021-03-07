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
        context.setVariable("header", "Purchase confirmation CinemaApp");
        context.setVariable("title", "Thank You for trusting our company!");
        context.setVariable("description", "Purchase details in attachment. \n" +
                "You can also find pdf file ready to download on our cinema site. \n" +
                "Enjoy the show! :)");
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
