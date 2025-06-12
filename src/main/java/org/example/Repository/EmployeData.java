package org.example.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.Entite.Employe;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EmployeData implements EmployeFile{
    //Spécifi le chemin de notre fichier Json
    private final String FILE_EMPLOYE = "/src/main/java/org/example/json/employes.json";
    //fichier Json pour lire et ecrire nos données
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<Employe> lireJson() {
        try(Reader reader = new FileReader(FILE_EMPLOYE)) {
            // Cherche le type générique de l'objet Employe
            Type type = new TypeToken<List<Employe>>(){}.getType();

            // Désérialisation d'une données dans la liste des employés
            List<Employe> employes = gson.fromJson(reader,type);

            // Si employe est vid, il retoure juste une liste vide
            return employes != null ? employes : new ArrayList<>();

        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture des employes : " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void ecrireJson(List<Employe> employes) {
        // Classe Writer pour ecrire nos data dans le json
        try {
            // Objet writer ou nous specifions le chemin d'accès vers notre fichier
            // Necessaire dans la methode toJson de l'objet gson
            Writer writer = new FileWriter(FILE_EMPLOYE);
            // Transforme la liste des employes aux formats json
            gson.toJson(employes, writer);
            // fermer la session du writer
            writer.close();
        } catch ( IOException e ) {
            System.out.println(e.getMessage());
        }

    }
}
