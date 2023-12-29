package facade;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import database.ProxyProtection;
import models.Medicine;
import models.PillMedicine;
import models.SyrupMedicine;
import models.Transaction;
import models.User;

public class CustomerMenuFacade {
    private User user;
    private ProxyProtection db;
    private Scanner in;

    public CustomerMenuFacade(User user) {
        this.user = user;
        db = new ProxyProtection(user);
        this.in = new Scanner(System.in);
        mainMenu();
    }

    private void mainMenu(){
        int opt;
        while(true){
            System.out.println("===== CUSTOMER MENU =====");
            System.out.println("1. Cart Menu");
            System.out.println("2. Transaction List");
            System.out.println("3. Logout");
            System.out.print(">> ");
            opt = in.nextInt();
            in.nextLine();
            switch (opt) {
                case 1:
                    cartMenu();
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

    private void cartMenu(){
        Transaction cart = new Transaction(this.user);
        int opt;

        while(true){
            if(cart.getMedicineList().isEmpty()){
                System.out.println("There's no item in your cart!");
            }else{
                int index = 0;
                System.out.println("======================= CART =======================");
                System.out.printf("| %-3s | %-10s | %-12s | %-5s |\n", "Num", "ID", "Name", "Price");
                for(Medicine med : cart.getMedicineList()){
                    System.out.printf("| %-3s | %-10s | %-12s | %-5d |\n", ++index, med.getId(), med.getName(), med.getPrice());
                }
            }

            System.out.println("===== CART MENU =====");
            System.out.println("1. Add to cart");
            System.out.println("2. Remove from cart");
            System.out.println("3. Checkout");
            System.out.println("4. EXIT");
            System.out.print(">> ");
            opt = in.nextInt();
            in.nextLine();
            switch (opt) {
                case 1:
                    addMenu(cart);
                    break;
                case 2:
                    removeMenu(cart);
                    break;
                case 3:
                    checkout(cart);
                    return;
                case 4:
                    return;
                default:
                    break;
            }
        }
    }

    private void addMenu(Transaction cart){
        Map<String, Medicine> medicineList = db.getAllMedicine();

        System.out.println("======================= MEDICINE LIST =======================");
        if(medicineList.isEmpty()){
            System.out.println("There's no medicine recorded!");
            return;
        }else{
            System.out.printf("| %-10s | %-12s | %-5s | %-5s | %-10s |\n", "ID", "Name", "Dosage", "Stock", "Price");
            for (Map.Entry<String, Medicine> med : medicineList.entrySet()) {
                if(med.getValue() instanceof PillMedicine){
                    PillMedicine pillMedicine = (PillMedicine) med.getValue();
                    System.out.printf("| %-10s | %-12s | %-5d | %-5d | %-10d |\n", med.getKey(), med.getValue().getName(), pillMedicine.getDosage(), med.getValue().getStock(), med.getValue().getPrice());
                }else{
                    SyrupMedicine syrupMedicine = (SyrupMedicine) med.getValue();
                    System.out.printf("| %-10s | %-12s | %-5d | %-5d | %-10d |\n", med.getKey(), med.getValue().getName(), syrupMedicine.getVolume(), med.getValue().getStock(), med.getValue().getPrice());
                }
            }
        }  
        
        Medicine med;
        while(true){
            System.out.print("Medicine ID to add: ");
            String id = in.nextLine();
            med = db.getOneMedicine(id);
            if(med == null){
                System.out.println("[!] There's no medicine with this id");
            }else break;
        }

        cart.addMedicine(med);
    }

    private void removeMenu(Transaction cart){
        while(true){
            System.out.print("Item number to remove from cart: ");
            int num = in.nextInt();
            in.nextLine();
            if(num > cart.getMedicineList().size()){
                System.out.println("[!] There's no medicine in this index");
            }else{
                cart.settotalPrice(cart.gettotalPrice() - cart.getMedicineList().get(num-1).getPrice());
                cart.removeMedicine(cart.getMedicineList().get(num-1));;
                break;
            }
        }
    }

    private void checkout(Transaction cart){
        System.out.println("You need to pay Rp." + cart.gettotalPrice());
        System.out.println("Press any key to pay...");
        in.nextLine();
        db.addTransaction(cart);
        for (Medicine med : cart.getMedicineList()) {
            med.setStock(med.getStock() - 1);
        }
        System.out.println("Payment has been received! press any key to continue...");
        in.nextLine();
    }

    private void transactionMenu(){
        ArrayList<Transaction> transactionList = db.getUserTransaction(this.user);
        if(transactionList.isEmpty()){
            System.out.println("There's no transaction recorded!");
            System.out.println("Press any button to continue");
            in.nextLine();
            return;
        }

        int index = 0;
        System.out.println("======================= TRANSACTION LIST =======================");
        for (Transaction trans : transactionList) {
            System.out.printf("| %-40s |\n", "Transaction number " + ++index);
            System.out.printf("| %-10s | %-12s | %-5s |\n", "ID", "Name", "Price");
            for(Medicine med : trans.getMedicineList()){
                System.out.printf("| %-10s | %-12s | %-5d |\n", med.getId(), med.getName(), med.getPrice());
            }
        }
    }
}
