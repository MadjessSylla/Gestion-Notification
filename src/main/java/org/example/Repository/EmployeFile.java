package org.example.Repository;

import org.example.Entite.Employe;

import java.util.List;

public interface EmployeFile {
    List<Employe> lireJson();
    void ecrireJson (List<Employe> employes);
}
