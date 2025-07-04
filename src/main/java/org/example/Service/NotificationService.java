package org.example.Service;

import org.example.Entite.Employe;
import org.example.Entite.Observer;

import java.util.ArrayList;
import java.util.List;

public class NotificationService implements Diffuseur {
    private List<Observer> abonnes = new ArrayList<>();

    @Override
    public void abonner(Observer observer) {
        if (!abonnes.contains(observer)) {
            abonnes.add(observer);
        }
    }

    @Override
    public void desabonner(Observer observer) {
        abonnes.remove(observer);
    }

    @Override
    public void notifierAbonner(Observer expediteur, String message) {
        for (Observer observer : abonnes) {
            if (observer != expediteur) { // Ne pas notifier l'expéditeur
                observer.envoyer(message, (Employe) expediteur);
            }
        }
    }
}