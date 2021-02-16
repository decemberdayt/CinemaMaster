package pl.cinema.springboot.mail;

import javax.activation.DataSource;

public interface EmailSender {
    void sendEmail(String to, String subject, String content, DataSource confirm);
}
