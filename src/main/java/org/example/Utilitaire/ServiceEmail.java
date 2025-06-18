package org.example.Utilitaire;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;

public class ServiceEmail {
    private static final Properties configProps = new Properties();

    static {
        try(InputStream input = ServiceEmail.class.getClassLoader().getResourceAsStream("Email.properties")){
            if (input == null) {
                throw new RuntimeException("Le fichier Email.properties n'existe pas");
            }
            configProps.load(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void envoyerEmail(String destinataire, String sujet, String contenu) {
        Properties mailProps = new Properties();
        mailProps.put("mail.smtp.host", configProps.getProperty("mail.smtp.host"));
        mailProps.put("mail.smtp.port", configProps.getProperty("mail.smtp.port"));
        mailProps.put("mail.smtp.auth", configProps.getProperty("mail.smtp.auth"));
        mailProps.put("mail.smtp.starttls.enable", configProps.getProperty("mail.smtp.starttls.enable"));

        final String email = configProps.getProperty("mail.smtp.email");
        final String password = configProps.getProperty("mail.smtp.password");

        Session session = Session.getInstance(mailProps, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire));
            message.setSubject(sujet);
            message.setText(contenu);
            Transport.send(message);
            System.out.println("Email envoyé à " + destinataire);
        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi de l'email: " + e.getMessage());
        }
    }
}