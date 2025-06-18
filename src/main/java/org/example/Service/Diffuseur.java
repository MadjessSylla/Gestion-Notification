package org.example.Service;

import org.example.Entite.Observer;

public interface Diffuseur {
    void abonner(Observer observer);
    void desabonner(Observer observer);
    void notifierAbonner(Observer expediteur, String message);
}