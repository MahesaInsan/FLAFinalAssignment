package facade;

import java.util.Scanner;

import database.ProxyProtection;
import models.Admin;
import models.Customer;
import models.User;

public class RegistrationService {
    CustomerMenuFacade customerMenu;
    AdminMenuFacade adminMenu;
    ProxyProtection db;
    Scanner in;

    public RegistrationService(){
        this.in = new Scanner(System.in);
        this.db = new ProxyProtection();
        while(true){
            System.out.println("===== LANDING PAGE =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. EXIT");
            System.out.print(">> ");
            int opt = in.nextInt();
            in.nextLine();
            switch (opt) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
        
    }

    public void login(){
        while(true){
            System.out.println("===== LOGIN MENU =====");
            System.out.print("Username: ");
            String username = in.nextLine();
            System.out.print("Password: ");
            String password = in.nextLine();
            User user = db.login(username, password);
            if(user == null){
                System.out.println("Wrong credential");
            }else{
                if(user.getIsAdmin()){
                    adminMenu = new AdminMenuFacade(user);
                }else customerMenu = new CustomerMenuFacade(user);
                return;
            }
        }
    }

    public void register(){
        while(true){
            System.out.println("===== REGISTER MENU =====");
            System.out.print("Username: ");
            String username = in.nextLine();
            System.out.print("Password: ");
            String password = in.nextLine();
            char admin;
            do{
                System.out.print("Admin Privilege [T | F]: ");
                admin = in.next().charAt(0);
                in.nextLine();
            }while(admin != 'T' && admin != 'F');

            User user = db.getOneUser(username);
            if(user == null){
                if(admin == 'T'){
                    user = new Admin(username, password);
                    db.addUser(user);
                    adminMenu = new AdminMenuFacade(user);
                }else{
                    user = new Customer(username, password); 
                    db.addUser(user);
                    customerMenu = new CustomerMenuFacade(user);
                }
                return;
            }else{
                System.out.println("User already exist");
            }
        }
    }
}
