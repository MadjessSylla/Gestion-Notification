package org.example.Entite;

public class Admin extends Employe implements Observer {
    public Admin(String email, String password) {
        super("Admin", "System", email, password, "00000000", true);
    }

    @Override
    public void envoyer(String message, Employe expediteur) {
        // Implémentation spécifique pour l'admin si nécessaire
    }
}