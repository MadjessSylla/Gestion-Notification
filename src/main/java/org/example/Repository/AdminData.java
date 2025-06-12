package org.example.Repository;

import org.example.Entite.Admin;

import java.util.List;

public class AdminData implements AdminFile {
    @Override
    public void ecrireJson(List<Admin> employes) {

    }

    @Override
    public List<Admin> lireJson() {
        return List.of();
    }
}
