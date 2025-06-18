package org.example.Entite;

import org.example.Utilitaire.ServiceEmail;

import java.util.ArrayList;
import java.util.List;

public class Employe implements Observer {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String telephone;
    private boolean status;
    private List<String> notifications;

    public Employe(String nom, String prenom, String email, String password, String telephone, Boolean status) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.status = status;
        this.notifications = new ArrayList<>();
    }
    public void afficherNotifications() {
        System.out.println("Notifications pour " + prenom + " " + nom + ":");
        if (notifications.isEmpty()) {
            System.out.println("Aucune notification.");
        } else {
            for (String notification : notifications) {
                System.out.println("- " + notification);
            }
        }
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<String> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<String> notifications) {
        this.notifications = notifications;
    }

    @Override
    public void envoyer(String message, Employe expediteur) {
        String notification = "Message de " + expediteur.getNom() + " " + expediteur.getPrenom() + ": " + message;
        notifications.add(notification);
        System.out.println("Nouveau message de: " + expediteur.getNom() + " " + expediteur.getPrenom());
        System.out.println("contenu: "+message);
        ServiceEmail.envoyerEmail(this.email,"notification",message);
    }
}
