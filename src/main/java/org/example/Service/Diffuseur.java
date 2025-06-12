package org.example.Service;

import org.example.Entite.Employe;
import org.example.Entite.Observer;

import java.util.List;

public interface Diffuseur {
    void abonner(Observer observer);
    void desabonner(Observer observer);
    void envoyerMessage(List<Employe> employe, String message);
}
