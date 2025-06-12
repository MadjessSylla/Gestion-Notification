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
        List<Employe> employes = new ArrayList<Employe>();
        //initialiser des données par séfaut
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
            System.out.println("Menu d'authentification");
            System.out.println("(1) Se connecter");
            System.out.println("(2) Voir notifications");
            System.out.println("(3) Envoyer message");
            System.out.println("(4) Quitter");
            System.out.print("Saisissez votre choix : ");

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
                            System.out.println("(1) Creer un employes");
                            System.out.println("(2) Supprimer un employes");
                            System.out.println("(3) Lister un employes");
                            System.out.println("(4) Envoyer message");
                            System.out.println("(5) Voir Notification");
                            System.out.println("(6) Quitter");
                            System.out.print("Saisissez votre choix : ");
                            int choix2 = scanner.nextInt();
                            scanner.nextLine();
                            switch (choix2) {
                                case 1://creation d'un employé
                                    System.out.println("Entrez le nom de l'employes");
                                    String nom = scanner.nextLine();
                                    System.out.println("Entrez le prenom de l'employes");
                                    String prenom = scanner.nextLine();
                                    System.out.println("Entrez l'email de l'employes");
                                    String email = scanner.nextLine();
                                    System.out.println("Entrez le mot de passe");
                                    String mot = scanner.nextLine();
                                    System.out.println("Entrez le numero de telephone de l'employes");
                                    String telephone = scanner.nextLine();
                                    employes.add(new Employe(nom,prenom,email,mot,telephone,false));
                                    break;
                                case 2://supprimé un employé
                                    System.out.println("Entrez le nom et le prénom de l'employes");

                                    // Recupère les identifiants de l'employé à supprimer
                                    System.out.print("Saisir le nom : ");
                                    String NomEmp = scanner.nextLine();
                                    System.out.print("Saisir le prénom : ");
                                    String PrenomEmp = scanner.nextLine();
                                    System.out.println("Saisir le email : ");
                                    String EmailEmp = scanner.nextLine();

                                    // Récupère l'employé dans le tableau
                                    Employe empRemove = null;

                                    for(Employe emp: employes) {
                                        if (emp.getNom().equals(NomEmp) && emp.getPrenom().equals(PrenomEmp) && emp.getEmail().equals(EmailEmp)) {
                                            empRemove = emp;
                                        }else {
                                            System.out.println("Employé introuvable");
                                            break;
                                        }
                                    }

                                    System.out.print("Voulez-vous vraiment supprimer cet employes (y/n) : ");
                                    String reponse = scanner.nextLine();
                                    if (reponse.equalsIgnoreCase("y")) {
                                        employes.remove(empRemove);
                                        System.out.println("Employe supprimé avec succes");
                                    } else if (reponse.equalsIgnoreCase("n")) {

                                        break;
                                    } else {
                                        System.out.println("impossible de supprimer cet employes (y/n) : ");
                                        continue;
                                    }
                                    break;
                                    case 3: //Lister les employés
                                        System.out.println("Liste des employes: ");
                                        for(Employe emp: employes) {
                                            String statut = (emp.getStatus()) ? "Abonné": "Non abonné";
                                            System.out.printf("%s %s (%s) - %s\n",emp.getNom(),emp.getPrenom(),emp.getEmail(), statut);
                                        }
                                        break;
                                   /* case 4: //Envoyer message
                                        System.out.println("Entrez le message à envoyer aux employés :");
                                        String message = scanner.nextLine();
                                        serviceNotification.notifierAbonner(Employe, message);
                                        System.out.println("Message envoyé à tous les employés.");
                                        break;
                                        case 5:// Voir notifications
                                            for (Employe emp : employes) {
                                                emp.afficherNotifications();
                                                System.out.println("-------------------------");
                                            }
                                            break;*/


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