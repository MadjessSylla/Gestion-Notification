package org.example.Entite;


import java.time.LocalDate;

public class Notification {
    private String titre;
    private String contenu;
    private LocalDate date;
    private Employe destinataire;

    public Notification(String titre, String contenu, LocalDate date, Employe destinataire) {
        this.titre = titre;
        this.contenu = contenu;
        this.date = date;
        this.destinataire = destinataire;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Employe getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Employe destinataire) {
        this.destinataire = destinataire;
    }
}
