package test;

import entities.Machine;
import entities.Salle;
import services.MachineService;
import services.SalleService;
import util.HibernateUtil;

import java.util.List;

/**
 * Programme principal de démonstration et validation de la persistance Hibernate.
 */
public class MainTest {

    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println("   INITIALISATION & PERSISTANCE HIBERNATE / MYSQL");
        System.out.println("=============================================================");

        SalleService salleService = new SalleService();
        MachineService machineService = new MachineService();

        // 1. Création d'une salle
        Salle salleA = new Salle();
        salleA.setCode("S1");
        salleA.setLibelle("Salle Informatique");

        boolean salleCreee = salleService.create(salleA);
        System.out.println("-> Salle S1 créée avec succès : " + salleCreee + " (ID: " + salleA.getId() + ")");

        // 2. Création et association de machines
        Machine machine1 = new Machine();
        machine1.setRef("M-101");
        machine1.setMarque("Dell");
        machine1.setSalle(salleA);
        machineService.create(machine1);

        Machine machine2 = new Machine();
        machine2.setRef("M-102");
        machine2.setMarque("HP");
        machine2.setSalle(salleA);
        machineService.create(machine2);

        System.out.println("-> Machines M-101 (Dell) et M-102 (HP) insérées avec succès !");
        System.out.println("=============================================================");

        // 3. Affichage de toutes les salles
        System.out.println("--- Liste de toutes les salles (getAll) ---");
        List<Salle> salles = salleService.getAll();
        for (Salle s : salles) {
            System.out.println("  * " + s);
        }

        // 4. Test de la requête personnalisée HQL findByMarque
        System.out.println("--- Recherche HQL : Machines de marque 'Dell' ---");
        List<Machine> dellMachines = machineService.findByMarque("Dell");
        for (Machine m : dellMachines) {
            System.out.println("  * " + m);
        }

        System.out.println("=============================================================");
        System.out.println("Données insérées et interrogées avec succès !");
        System.out.println("=============================================================");

        // Fermeture propre de la SessionFactory
        HibernateUtil.shutdown();
    }
}
