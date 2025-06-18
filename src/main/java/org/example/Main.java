package org.example;

import org.example.Entite.Admin;
import org.example.Entite.Employe;
import org.example.Repository.EmployeData;
import org.example.Service.Diffuseur;
import org.example.Service.NotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        //Initialisation d'un tableau employé
        List<Employe> employes = new ArrayList<Employe>();
        //initialiser des données par défaut
        if (employes.isEmpty()) {
            employes.add(new Employe("Maiga","Djibril","maigadjibril444@gmail.com","djibril@123","77466178", false));
            employes.add(new Employe("Dolo","Oumar","dolooumar60@gmail.com","dolo@123","93761029",false));
        }
        //Creation d'un admin par defaut
        Admin admin = new Admin("madyehsylla427@gmail.com","admin@123");

        //Utilisation du type diffuseur
        Diffuseur serviceNotification = new NotificationService();
        Scanner scanner = new Scanner(System.in);
        //Boucle du menu principal
        while(true) {
            //1er menu :Menu d'authentication
            System.out.println("\nMenu d'authentification");
            System.out.println("(1) Se connecter comme Admin");
            System.out.println("(2) Se connecter comme Employé");
            System.out.println("(3) Quitter");
            System.out.print("Saisissez votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1: // Connexion Admin
                    System.out.print("Entrez votre email: ");
                    String loginAdmin = scanner.nextLine();
                    if (loginAdmin.equals(admin.getEmail())){
                        System.out.print("Entrez votre mot de passe: ");
                        String passwordAdmin = scanner.nextLine();
                        if (passwordAdmin.equals(admin.getPassword())){
                            menuAdmin(admin, employes, serviceNotification, scanner);
                        } else {
                            System.out.println("Mot de passe incorrect!");
                        }
                    } else {
                        System.out.println("Email admin incorrect!");
                    }
                    break;

                case 2: // Connexion Employé
                    System.out.print("Entrez votre email: ");
                    String emailEmp = scanner.nextLine();
                    System.out.print("Entrez votre mot de passe: ");
                    String passwordEmp = scanner.nextLine();

                    Employe employeConnecte = null;
                    for (Employe emp : employes) {
                        if (emp.getEmail().equals(emailEmp) && emp.getPassword().equals(passwordEmp)) {
                            employeConnecte = emp;
                            break;
                        }
                    }

                    if (employeConnecte != null) {
                        menuEmploye(employeConnecte, serviceNotification, scanner);
                    } else {
                        System.out.println("Email ou mot de passe incorrect!");
                    }
                    break;

                case 3:
                    System.out.println("Fermeture du programme");
                    scanner.close();
                    return;

                default:
                    System.out.println("Choix invalide!");
            }
        }
    }

    private static void menuAdmin(Admin admin, List<Employe> employes, Diffuseur serviceNotification, Scanner scanner) {
        while(true) {
            System.out.println("\nMenu Admin");
            System.out.println("(1) Creer un employe");
            System.out.println("(2) Supprimer un employe");
            System.out.println("(3) Lister les employes");
            System.out.println("(4) Envoyer message");
            System.out.println("(5) Voir Notifications");
            System.out.println("(6) Menu Employé");
            System.out.println("(7) Quitter");
            System.out.print("Saisissez votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1: // creation d'un employé
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
                    employes.add(new Employe(nom, prenom, email, mot, telephone, false));
                    System.out.println("Employe créé avec succès!");
                    break;

                case 2: // supprimer un employé
                    System.out.println("Liste des employes:");
                    for (int i = 0; i < employes.size(); i++) {
                        System.out.println((i+1) + ". " + employes.get(i).getNom() + " " + employes.get(i).getPrenom());
                    }
                    System.out.print("Choisissez le numéro de l'employé à supprimer: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();

                    if (index > 0 && index <= employes.size()) {
                        System.out.print("Voulez-vous vraiment supprimer " + employes.get(index-1).getNom() + " " + employes.get(index-1).getPrenom() + "? (y/n): ");
                        String reponse = scanner.nextLine();
                        if (reponse.equalsIgnoreCase("y")) {
                            employes.remove(index-1);
                            System.out.println("Employe supprimé avec succès!");
                        }
                    } else {
                        System.out.println("Numéro invalide!");
                    }
                    break;

                case 3: // Lister les employés
                    System.out.println("Liste des employes: ");
                    for(Employe emp: employes) {
                        String statut = (emp.getStatus()) ? "Abonné" : "Non abonné";
                        System.out.printf("%s %s (%s) - %s\n", emp.getNom(), emp.getPrenom(), emp.getEmail(), statut);
                    }
                    break;

                case 4: // Envoyer message
                    System.out.println("Entrez le message à envoyer aux employés :");
                    String message = scanner.nextLine();
                    for (Employe emp : employes) {
                        if (emp.getStatus()) { // Envoyer seulement aux abonnés
                            serviceNotification.notifierAbonner(admin, message);
                        }
                    }
                    System.out.println("Message envoyé à tous les employés abonnés.");
                    break;

                case 5: // Voir notifications
                    for (Employe emp : employes) {
                        emp.afficherNotifications();
                        System.out.println("-------------------------");
                    }
                    break;

                case 6: // Menu Employé
                    menuEmploye(admin, serviceNotification, scanner);
                    break;

                case 7: // Quitter
                    return;

                default:
                    System.out.println("Choix invalide!");
            }
        }
    }

    private static void menuEmploye(Employe employe, Diffuseur serviceNotification, Scanner scanner) {
        while(true) {
            System.out.println("\nMenu Employé - " + employe.getPrenom() + " " + employe.getNom());
            System.out.println("(1) S'abonner aux notifications");
            System.out.println("(2) Se désabonner des notifications");
            System.out.println("(3) Voir mes notifications");
            System.out.println("(4) Quitter");
            System.out.print("Saisissez votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1: // S'abonner
                    if (!employe.getStatus()) {
                        serviceNotification.abonner(employe);
                        employe.setStatus(true);
                        System.out.println("Vous êtes maintenant abonné aux notifications.");
                    } else {
                        System.out.println("Vous êtes déjà abonné!");
                    }
                    break;

                case 2: // Se désabonner
                    if (employe.getStatus()) {
                        serviceNotification.desabonner(employe);
                        employe.setStatus(false);
                        System.out.println("Vous êtes maintenant désabonné des notifications.");
                    } else {
                        System.out.println("Vous n'êtes pas abonné!");
                    }
                    break;

                case 3: // Voir notifications
                    employe.afficherNotifications();
                    break;

                case 4: // Quitter
                    return;

                default:
                    System.out.println("Choix invalide!");
            }
        }
    }
}