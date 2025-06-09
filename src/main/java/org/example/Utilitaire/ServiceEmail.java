package org.example.Utilitaire;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.InputStream;
import java.util.Properties;

public class ServiceEmail {
    private static final Properties prop = new Properties();

    static {
        try(InputStream input = ServiceEmail.class.getClassLoader().getResourceAsStream("Email.properties")){
            if (input == null){
                throw new RuntimeException("Le fichier Email.properties n'existe pas");
            }
            prop.load(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static void envoyerEmail(String destinataire, String sujet, String contenu) {
    //configurer  les proporités pour Jakarta Mail
        Properties prop = new Properties();
        prop.put("mail.smtp.host", prop.getProperty("mail.smtp.host"));
        prop.put("mail.smtp.port", prop.getProperty("mail.smtp.port"));
        prop.put("mail.smtp.auth", prop.getProperty("mail.smtp.auth"));
        prop.put("mail.smtp.starttls.enable", prop.getProperty("mail.smtp.starttls.enable"));
    // config des propriétés email et password
    final String email = prop.getProperty("mail.smtp.email");
    final String password = prop.getProperty("mail.smtp.password");
    //creer une session avec authentification
    Session session = Session.getInstance(prop , new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(email, password);
        }
    });
    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email));
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(destinataire));
        message.setSubject(sujet);
        message.setText(contenu);
        //envoie du message
        Transport.send(message);
        System.out.println("email envoyé à " + destinataire);
    } catch (MessagingException e) {
        System.out.println("Erreur lors de l'envoi de email: " + e.getMessage());
    }


    }
}
