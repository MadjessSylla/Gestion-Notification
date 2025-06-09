package org.example;

import org.example.Entite.Admin;
import org.example.Entite.Employe;
import org.example.Service.Diffuseur;
import org.example.Service.NotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
 //Initialisation d'un tableau employé
        List<Employe> employe = new ArrayList<Employe>();
        //initialiser des données par séfaut
        if (employe.isEmpty()) {
            employe.add(new Employe("Maiga","Djibril","maigadjibril444@gmail.com","djibril@123","77466178", false));
            employe.add(new Employe("Dolo","Oumar","dolooumar60@gmail.com","dolo@123","93761029",false));
        }
        //Creation d'un admin par defaut
        Admin admin = new Admin("madyehsylla427@gmail.com","admin@123");

        //Utilisation du type diffuseur
        Diffuseur serviceNotification = new NotificationService();
        Scanner scanner = new Scanner(System.in);
        //Boucle du menu principal
        while(true) {
            //1er menu :Menu d'authentication
            System.out.println("Menu d'authentification");
            System.out.println("(1) Se connecter");
            System.out.println("(2) Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1://Entrez le login
                    System.out.print("Entrez votre login: ");
                    String login = scanner.nextLine();
                    if (login.equals(admin.getEmail())){
                        System.out.print("Entrez votre mot de passe: ");
                        String password = scanner.nextLine();
                        if (password.equals(admin.getPassword())){
                            System.out.println("Menu Admin");
                            System.out.println("(1) Creer un employe");
                            System.out.println("(2) Supprimer un employe");
                            System.out.println("(3) Lister un employe");
                            System.out.println("(4) Quitter");
                            int choix2 = scanner.nextInt();
                            scanner.nextLine();
                            switch (choix2) {
                                case 1://creation d'un employé
                                    System.out.println("Entrez le nom de l'employe");
                                    String nom = scanner.nextLine();
                                    System.out.println("Entrez le prenom de l'employe");
                                    String prenom = scanner.nextLine();
                                    System.out.println("Entrez l'email de l'employe");
                                    String email = scanner.nextLine();
                                    System.out.println("Entrez le mot de passe");
                                    String mot = scanner.nextLine();
                                    System.out.println("Entrez le numero de telephone de l'employe");
                                    String telephone = scanner.nextLine();
                                    employe.add(new Employe(nom,prenom,email,mot,telephone,false));
                            }

                    }

                };
                    break;
                default:
                    System.out.println("Fermeture du programme");
                    scanner.close();
                    return;
            }

        }

    }
}