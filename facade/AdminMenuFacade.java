package facade;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import database.ProxyProtection;
import factory.PillMedicineFactory;
import factory.SyrupMedicineFactory;
import models.Medicine;
import models.PillMedicine;
import models.SyrupMedicine;
import models.Transaction;
import models.User;

public class AdminMenuFacade{
    private ProxyProtection db;
    private Scanner in;

    public AdminMenuFacade(User user) {
        db = new ProxyProtection(user);
        this.in = new Scanner(System.in);
        mainMenu();
    }

    private void mainMenu(){
        int opt;
        while(true){
            System.out.println("===== ADMIN MENU =====");
            System.out.println("1. Medicine Management");
            System.out.println("2. Transaction List");
            System.out.println("3. Logout");
            System.out.print(">> ");
            opt = in.nextInt();
            in.nextLine();
            switch (opt) {
                case 1:
                    medicineMenu();
                    break;
                case 2:
                    transactionMenu();
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }

    private void transactionMenu(){
        ArrayList<Transaction> transactionList = db.getAllTransaction();
        if(transactionList.isEmpty()){
            System.out.println("There's no transaction recorded!");
            System.out.println("Press any button to continue");
            in.nextLine();
            return;
        }

        System.out.println("======================= TRANSACTION LIST =======================");
        for (Transaction trans : transactionList) {
            System.out.printf("| %-40s |\n", trans.getUser().getUsername() + "'s Transaction");
            System.out.printf("| %-10s | %-12s | %-5s |\n", "ID", "Name", "Price");
            for(Medicine med : trans.getMedicineList()){
                System.out.printf("| %-10s | %-12s | %-5d |\n", med.getId(), med.getName(), med.getPrice());
            }
        }
    }

    private void medicineMenu(){
        Map<String, Medicine> medicineList = db.getAllMedicine();
        int opt;
    
        
        while(true){
            System.out.println("======================= MEDICINE LIST =======================");
            if(medicineList.isEmpty()){
                System.out.println("There's no medicine recorded!");
            }else{
                System.out.printf("| %-10s | %-12s | %-5s | %-5s | %-10s | %-15s |\n", "ID", "Name", "Dosage", "Stock", "Price", "Status");
                for (Map.Entry<String, Medicine> med : medicineList.entrySet()) {
                    if(med.getValue() instanceof PillMedicine){
                        PillMedicine pillMedicine = (PillMedicine) med.getValue();
                        System.out.printf("| %-10s | %-12s | %-5d | %-5d | %-10d | %-15s |\n", med.getKey(), med.getValue().getName(), pillMedicine.getDosage(), med.getValue().getStock(), med.getValue().getPrice(), med.getValue().getStatus().showState());
                    }else{
                        SyrupMedicine syrupMedicine = (SyrupMedicine) med.getValue();
                        System.out.printf("| %-10s | %-12s | %-5d | %-5d | %-10d | %-15s |\n", med.getKey(), med.getValue().getName(), syrupMedicine.getVolume(), med.getValue().getStock(), med.getValue().getPrice(), med.getValue().getStatus().showState());
                    }
                }
            }

            System.out.println("===== MEDICINE MENU =====");
            System.out.println("1. Add New Medicine");
            System.out.println("2. Update Medicine Stock");
            System.out.println("3. Remove Medicine");
            System.out.println("4. Back to Admin Menu");
            System.out.print(">> ");
            opt = in.nextInt();
            in.nextLine();
            switch (opt) {
                case 1:
                    addMenu();
                    break;
                case 2:
                    updateMenu();
                    break;
                case 3:
                    removeMenu();
                    break;
                case 4:
                    return;
                default:
                    break;
            }
        }
    }

    private void addMenu(){
        System.out.println("===== ADD MEDICINE =====");

        String choice = "";
        do{
            System.out.print("Medicine type [Pills | Syrup]: ");
            choice = in.nextLine();
        }while(!choice.equals("Pills") && !choice.equals("Syrup"));

        String id;
        while(true){
            System.out.print("Medicine ID: ");
            id = in.nextLine();
            if(db.getOneMedicine(id) != null){
                System.out.println("[!] Medicine with this id is already inputted");
            }else break;
        }

        System.out.print("Medicine name: ");
        String name = in.nextLine();

        System.out.print("Medicine volume/dosage: ");
        int measure = in.nextInt();
        in.nextLine();
        
        System.out.print("Medicine stock: ");
        int stock = in.nextInt();
        in.nextLine();

        System.out.print("Medicine price: Rp.");
        int price = in.nextInt();
        in.nextLine();

        if(choice.equals("Pills")){
            PillMedicineFactory pillFactory = new PillMedicineFactory();
            db.addMedicine(pillFactory.createMedicine(id, name, measure, stock, price));
        }else{
            SyrupMedicineFactory syrupFactory = new SyrupMedicineFactory();
            db.addMedicine(syrupFactory.createMedicine(id, name, measure, stock, price));
        }
    }

    public void updateMenu(){
        System.out.println("UPDATE MEDICINE STOCK");
        Medicine med;
        while(true){
            System.out.print("Medicine ID: ");
            String id = in.nextLine();
            med = db.getOneMedicine(id);
            if(med == null){
                System.out.println("[!] There's no medicine with this id");
            }else break;
        }
        System.out.print("How many you want to add: ");
        int stock = in.nextInt();
        in.nextLine();

        db.addMedStock(med, stock);
    }

    public void removeMenu(){
        Medicine med;
        while(true){
            System.out.print("Medicine ID to remove: ");
            String id = in.nextLine();
            med = db.getOneMedicine(id);
            if(med == null){
                System.out.println("[!] There's no medicine with this id");
            }else break;
        }

        db.deleteMedicine(med.getId());
    }

}
