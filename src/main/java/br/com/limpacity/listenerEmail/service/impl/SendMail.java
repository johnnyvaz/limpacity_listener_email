package br.com.limpacity.listenerEmail.service.impl;

import br.com.limpacity.producer.dto.NotificaEmailDTO;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;

public class SendMail {

    @Value("${email.username}")
    private String username;

    @Value("${email.password}")
    private String password;

    private static final Logger logger = Logger.getLogger(SendMail.class.getName());
    Session mailSession;

    public void enviarEmail(NotificaEmailDTO email) throws MessagingException {
        SendMail javaEmail = new SendMail();
        javaEmail.setMailServerProperties();
        javaEmail.draftEmailMessage();
        javaEmail.sendEmail();
    }

    private void setMailServerProperties() {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "465");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.startssl.enable", "true");
        mailSession = Session.getDefaultInstance(emailProperties, null);
    }

    private MimeMessage draftEmailMessage() throws AddressException, MessagingException {
        String[] toEmails = { "johnnyjohnnyjohnny@gmail.com" };
        String emailSubject = "Test de email automático";
        String emailBody = " corpo do email <b> limpacity.com</b>.";
        MimeMessage emailMessage = new MimeMessage(mailSession);
        /**
         * Set the mail recipients
         */
        for (int i = 0; i < toEmails.length; i++) {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }
        emailMessage.setSubject(emailSubject);
        /**
         * If sending HTML mail
         */
        emailMessage.setContent(emailBody, "text/html");
        /**
         * If sending only text mail
         */
        // emailMessage.setText(emailBody);// for a text email
        return emailMessage;
    }

    private void sendEmail() throws AddressException, MessagingException {
        /**
         * Sender's credentials
         */

        String emailHost = "smtp.zoho.com";
        Transport transport = mailSession.getTransport("smtps");
        transport.connect(emailHost, username, password);
        /**
         * Draft the message
         */
        MimeMessage emailMessage = draftEmailMessage();
        /**
         * Send the mail
         */
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Email enviado com sucesso.");
    }

}