package org.example.Repository;

import org.example.Entite.Admin;

import java.util.List;

public interface AdminFile  {
    List<Admin> lireJson();
    void ecrireJson(List<Admin> employes);
}
